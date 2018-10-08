package cn.itcast.scala.actor

import scala.actors.Actor

/**
  * react方式会复用线程，比receive更高效
  */
class YourActor extends Actor {

  override def act(): Unit ={
    // react 如果要反复执行消息处理，react外层要用loop，不能用while
    loop{
      react{
        case "start" =>{
          println("starting...")
          Thread.sleep(5000)
          println(Thread.currentThread().getName)
          println("started")
        }
        case "stop" =>{
          println("stopping...")
          Thread.sleep(8000)
          println(Thread.currentThread().getName)
          println("stopped...")
        }
        case "exit" =>{
          exit()
        }
      }
    }
  }

}

object YourActor{
  def main(args: Array[String]): Unit = {
    val actor=new YourActor
    actor.start()
    actor ! "start"
    actor ! "stop"
    Thread.sleep(10000)
    actor ! "exit"
  }
}
