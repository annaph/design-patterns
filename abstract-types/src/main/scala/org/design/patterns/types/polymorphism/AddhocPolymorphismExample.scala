package org.design.patterns.types.polymorphism

trait Adder[T] {

  def sum(a: T, b: T): T

}

object Adder {

  def sum[T: Adder](a: T, b: T): T =
    implicitly[Adder[T]].sum(a, b)

  implicit val int2Adder: Adder[Int] =
    _ + _

  implicit def numeric2Adder[T : Numeric]: Adder[T] =
    implicitly[Numeric[T]] plus(_, _)

  implicit val string2Adder: Adder[String] = (a, b) =>
    s"$a concatenated with $b"

}

object AddhocPolymorphismExample extends App {

  import Adder._

  System.out.println(s"The sum of 1 + 2 is ${sum(1, 2)}")
  System.out.println(s"The sum of 1.2 and 6.5 is ${sum(1.2, 6.5)}")
  System.out.println(s"The sum of abc + def is ${sum("abc", "def")}")

}
