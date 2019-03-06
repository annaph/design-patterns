package org.design.patterns.functional.implicits

object ImplicitExamples extends App {

  val number: Int = 7.6
  System.out.println(s"The integer value of 7.6 is $number")

  printAsciiString(List(72, 69, 76, 76, 79, 33))

  def printAsciiString(s: String): Unit =
    System.out.println(s)

}
