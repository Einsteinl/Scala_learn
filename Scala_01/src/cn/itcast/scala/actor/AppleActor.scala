package cn.itcast.scala.actor

import scala.actors.Actor

class AppleActor extends Actor{
  override def act(): Unit = {
    while (true){
      receive{
        case "start" => println("starting...")
        case SyncMsg(id,msg) =>{
          println(id +",sync "+msg)
          Thread.sleep(5000)
          sender ! ReplyMsg(3,"finished")
        }
        case AsyncMsg(id,msg) =>{
          println(id +",async "+msg)
          Thread.sleep(5000)
          sender ! ReplyMsg(3,"sfinished")
        }
      }
    }
  }
}

object AppleActor{
  def main(args: Array[String]): Unit = {
    val a=new AppleActor
    a.start()
    //异步消息
    a ! AsyncMsg(1,"hello actor")
    println("异步消息发送完成")
    //同步消息
                 //消息超时时间
     val content=a.!?(11000,SyncMsg(2,"hello actor"))

     println(content)
    //得到一个future对象
    val reply=a !! SyncMsg(3,"hello actor")
    //Thread.sleep(13000)
    println(reply.isSet)
    val c=reply.apply()
    println(reply.isSet)
    println(c)
  }
}

case class SyncMsg(id :Int,msg:String)
case class AsyncMsg(id:Int,msg:String)
case class ReplyMsg(id:Int,msg:String)
