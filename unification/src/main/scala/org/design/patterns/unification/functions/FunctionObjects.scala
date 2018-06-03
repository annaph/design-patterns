package org.design.patterns.unification.functions

class SumFunction extends Function2[Int, Int, Int] {

  override def apply(a: Int, b: Int): Int =
    a + b

}

class FunctionObjects {

  val sum = new SumFunction

  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int =
    f(a, b)

}

object FunctionObjects extends App {

  val obj = new FunctionObjects

  System.out.println(s"3 + 9 = ${obj.sum(3, 9)}")
  System.out.println(s"Calling run operation: ${obj runOperation(obj.sum, 10, 20)}")
  System.out.println(s"Using Math.max: ${obj runOperation(Math.max, 10, 20)}")

}
