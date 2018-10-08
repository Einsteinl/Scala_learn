package cn.itcast.scala.basic

/**
  * 循环
  */
object ForDemo {

  def main(args:Array[String]): Unit ={

    //for(i <-表达式)，表达式 1 to 10 返回一个Range(区间)
    for(i<- 1 to 10)
      println(i)

    //for(i <- 数组)
    var arr=Array("a","b","c")
    for(i <- arr){
      println(i)

    }

    //高级for循环 (嵌套循环)
    //每个生成器都可以带一个条件，注意：if 前面没有分号
    for(i <- 1 to 3; j <- 1 to 3 if i!= j)
      print((10*i+j)+" ")

    //for推导式：如果for循环的循环体以yield开始，则该循环会构建一个集合
    //每次迭代生成集合中的一个值

    val v=for(i <- 1 to 10) yield i*10
    println(v)



  }

}
