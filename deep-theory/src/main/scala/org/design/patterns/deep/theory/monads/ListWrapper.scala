package org.design.patterns.deep.theory.monads

import scala.collection.GenTraversableOnce

case class ListWrapper(list: List[Int]) {

  def map[B](f: Int => B): List[B] =
    list map f

  def flatMap[B](f: Int => GenTraversableOnce[B]): List[B] =
    list flatMap f

}

object ForComprehensionWithLists extends App {

  val l1 = List(1, 2, 3, 4)
  val l2 = List(5, 6, 7, 8)

  val result = for {
    x <- l1
    y <- l2
  } yield x * y

  System.out.println(s"The result is: $result")

}

object ForComprehensionWithObjects extends App {

  val wrapper1 = ListWrapper(List(1, 2, 3, 4))
  val wrapper2 = ListWrapper(List(5, 6, 7, 8))

  val result = for {
    x <- wrapper1
    y <- wrapper2
  } yield x * y

  System.out.println(s"The result is: $result")

}
