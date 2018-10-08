package cn.itcast.scala.actor

import scala.actors.{Actor, Future}
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * 读入文件，循环开启actor处理各个文件
  *
  */
class Task extends Actor{
  override def act(): Unit = {

    loop{
      react{
        case SubmitTask(filename) =>{
          //读整个文件，并作出转换 结果是Map[String,Int]
          //Map(peter -> 2, john -> 1, sally -> 1, pet -> 2, hello -> 2)
          val result=Source.fromFile(filename).getLines().flatMap(_.split(" ")).map((_,1)).toList.groupBy(_._1).mapValues(_.size)
         // println(result)
          sender ! ResultTask(result)
        }
        case StopTask =>{
          exit()

        }
      }
    }
  }
}
//提交类型
case class SubmitTask(filename: String)
//处理结果类型
case class ResultTask(result: Map[String,Int])
case object StopTask

object ActorWordCount {

  def main(args: Array[String]): Unit ={

    //存放Future的set集合
    val replySet=new HashSet[Future[Any]]()

    //存放Future中的SubmitTask
    val resultList=new ListBuffer[ResultTask]()

    //读取的文件
    val files=Array[String]("c://words.txt","c://words.log")
    for(f <- files){
      val actor=new Task
      //Future引用(各个文件的处理结果ResultTask)
      val reply=actor.start() !! SubmitTask(f)
      //将Actor回复的future存放放到future集合中
      replySet += reply

    }

    /**
      * 遍历future集合，将已完成的future中的ResultTask存到ResultTask集合resultList中
      */
    while(replySet.size>0){
      val toCompute=replySet.filter(_.isSet)
      for(f <- toCompute){
        val result=f.apply().asInstanceOf[ResultTask]
        resultList += result
        replySet -= f
      }
    }
    Thread.sleep(100)
//ListBuffer(Map(peter -> 2, john -> 1, sally -> 1, pet -> 2, hello -> 2), Map(peter -> 2, john -> 1, hell -> 1, sally -> 1, pet -> 1, hello -> 3, jerry -> 1))
    //val t1=resultList.map(_.result)
    //println(t1)
//ListBuffer((peter,2), (john,1), (sally,1), (pet,2), (hello,2), (peter,2), (john,1), (hell,1), (sally,1), (pet,1), (hello,3), (jerry,1))
    //val t2=resultList.flatMap(_.result)
   // println(t2)

    //对集聚了所有ResultTask的resultList进行计算
    //汇总功能
    val fr=resultList.flatMap(_.result).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
    println(fr)

  }

}
