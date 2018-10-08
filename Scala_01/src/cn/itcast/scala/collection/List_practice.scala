package cn.itcast.scala.collection

object List_practice {


    def main(args: Array[String]): Unit = {
      //创建一个List
      val lst0 = List(1,7,9,8,0,3,5,4,6,2)


      // 1.将lst0中每个元素乘以10后生成一个新的集合
      val list1=lst0.map(_*10)   //map是一个方法，里边传一个简化的函数
      println(list1)


      // 2.将lst0中的偶数取出来生成一个新的集合
      val list2=lst0.filter(_%2==0)  //符合条件的留下
      println(list2)


      // 3.将lst0排序后生成一个新的集合
      val list3=lst0.sorted
      println(list3)

      // 4.反转顺序
      val list4=lst0.sorted.reverse
      println(list4)


      // 5. 将lst0中的元素4个一组,类型为Iterator[List[Int]]
      val list5=lst0.grouped(4)
      println(list5)//list5是一个类型为Iterator[List[Int]]的迭代器     值为：non-empty iterator
      /*
        把一个迭代器执行toList时候就相当于把迭代器的指针指向末尾了
       */
      //println(list5.toList) //List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))
      println(list5.toList)
      println(list5)//empty iterator


      // 6.将Iterator转换成List
      val list6=lst0.grouped(4)
      println(list6.toList)      //List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))


      //7. map 和foreach区别
      /*
        map和foreach都是可以对每一个元素操作，但是map是对每一个元素操作完了还会返回一个新的集合
         而foreach则不会返回任何值
        */
      val arr=Array(1,2,3,4)
      print(arr.map(println ))
      print(arr.foreach(x=>println(x)))


      //8.将List压平  意思是说List里边套着很多List，压平之后就剩下外边的一个List

      //8.1首先准备一个list套list的数据结构
      val list8=lst0.grouped(4)
      println(list8)   //non-empty iterator
      val list8_1=list8.toList
      println(list8_1)  //List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))
      //8.2压平
      val  list8_2=list8_1.flatten
      println(list8_2)  //List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)

      //map是输出结果再保存到一个集合里边
      //foreach是对每一个元素进行操作并输出


      //将多个list压扁成一个List
      val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")

      println(lines.flatMap(_.split(" ")))
      //List(hello, tom, hello, jerry, hello, jerry, hello, kitty)
      //flatMap中的_表示一行内容 hello tom hello jerry  一共有三行  即对每行操作
      //flatMap(_.split(" ")) 中的_.split(" ") 就相当于"hello tom hello jerry".split(" ")


      println(lines.flatMap(_.split(" ")).map((_ ,1)))
      //List((hello,1), (tom,1), (hello,1), (jerry,1), (hello,1), (jerry,1), (hello,1), (kitty,1))
      //返回的List集合，集合里边每一个元素为元组  访问元组的第一个元素为_._1
      //例如：println((""hello",1)._1)  结果为hello
      //lines.flatMap(_.split(" ")).map((_ ,1))中的map((_ ,1) _表示每一个单词，1表示每出现一次计数为1



      println(lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1))
      //Map(tom -> List((tom,1)), kitty -> List((kitty,1)), jerry -> List((jerry,1), (jerry,1)), hello -> List((hello,1), (hello,1), (hello,1), (hello,1)))
      // lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1)中的groupBy(_._1)表示按照list中每个元组中的第一个字段分组即拿第一个字段作为key，返回结果是一个大Map
      //groupBy(_._1)中的第一个_表示list中的每一个元组,而  ._1  表示取每一个元组中的第一个元素




      println(lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)))
      //Map(tom -> 1, kitty -> 1, jerry -> 2, hello -> 4)
      // lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues()中的mapValues()仅仅会对value处理，处理完了把key 结合起来
      //  mapValues()中的第一个_表示map里边的value ，而value是一个list
      //lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))  中的foldLeft(0)是给一个初始值
      // (_+_._2)中的第一个_表示初始值或者累加过的值，第二个_表示List里边的元组，._2表示拿到元组中的第二个字段




      println(lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)).toList)
      // List((tom,1), (kitty,1), (jerry,2), (hello,4))
      // 转化为List



      println(lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)).toList.sortBy(_._2))
      //List((tom,1), (kitty,1), (jerry,2), (hello,4))
      //sortBy(_._2)中的第一个_ 表示每一个元组，第二个._2 每个元组中的第二个字段



      println(lines.flatMap(_.split(" ")).map((_ ,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)).toList.sortBy(_._2).reverse)
      //List((hello,4), (jerry,2), (kitty,1), (tom,1))
      //reverse表示降序排序


      //先按空格切分，在压平




      //并行计算求和
      println(lst0.par.sum)//45 并行求和
      println(lst0.par.reduce(_+_))//45 并行求和
      //lst0.par表示并行，根据CPU合数决定

      //化简：reduce

      //将非特定顺序的二元操作应用到所有元素


      //安装特点的顺序



      //折叠：有初始值（无特定顺序）
      println(lst0.par.fold(0)(_+_)) //45
      println(lst0.par.fold(10)(_+_))//125

      //折叠：有初始值（有特定顺序）
      println(lst0.foldLeft(0)(_+_))




      //聚合 aggregate
      val arr1 = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
      println(arr1.aggregate(0)(_+_.sum,_+_))// 先局部求和，在整体求和

      val ll = List(5,6,4,7)
      val l2 = List(1,2,3,4)

      //求并集
      println(ll.union(l2))

      //求交集
      println(ll.intersect(l2))
      //

      //求差集
      println(ll.diff(l2))
      //println(r3)



      /*val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
      lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
      lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t=>(t._1, t._2.size)).toList.sortBy(_._2).reverse*/
    }



}
