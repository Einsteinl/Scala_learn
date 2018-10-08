package cn.itcast.scala.actor

import scala.actors.Actor

object MyActor1 extends Actor {
  //重写act方法
  override def act(): Unit = {
    for(i<-1 to 10){
      println("actor-1"+i)
      Thread.sleep(1000)
    }
  }
}

object MyActor2 extends Actor{

  override def act(): Unit = {
    for(i <- 1 to 10){
      println("actor-2" + i)
      Thread.sleep(1000)
    }
  }
}

object ActorText extends  App{
  //启动Actor
  MyActor1.start()
  MyActor2.start()
}
