package cn.itcast.scala.casedemo

import scala.util.Random

/**
  * 匹配字符串
  */
object CaseDemo01 extends App {

  val arr=Array("YoshizawaAkiho", "YuiHatano", "AoiSola")
  val name=arr(Random.nextInt(arr.length))
  name match {
    case "YoshizawaAkiho" => println("吉泽老师。。。")
    case "YuiHatano" => println("波多老师。。。")
    case _ => println("真不知道你们在说什么。。。")
  }
}

/**
  * 匹配类型
  */
object CaseDemo02 extends App{
  val arr=Array("hello",1,2.0,CaseDemo02)
  val v=arr(Random.nextInt(4))
  println(v)
  v match {
    case x: Int => println("Int"+x)
      /*
      注意：case y: Double if(y >= 0) => ...
      模式匹配的时候还可以添加守卫条件。如不符合守卫条件，将掉入case _中
       */
    case y: Double if(y>=0) =>println("Double"+y)
    case z: String => println("String"+z)
    case _=> throw new Exception("not match exception")
  }
}
