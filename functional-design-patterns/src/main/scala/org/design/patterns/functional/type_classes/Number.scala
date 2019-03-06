package org.design.patterns.functional.type_classes

trait Number[T] {

  def plus(x: T, y: T): T

  def minus(x: T, y: T): T

  def multiply(x: T, y: T): T

  def divide(x: T, y: Int): T

  def sqrt(x: T): T

}

object Number {

  implicit object IntNumber extends Number[Int] {

    import Math.round

    override def plus(x: Int, y: Int): Int =
      x + y

    override def minus(x: Int, y: Int): Int =
      x - y

    override def multiply(x: Int, y: Int): Int =
      x * y

    override def divide(x: Int, y: Int): Int =
      round(x.toDouble / y.toDouble).toInt

    override def sqrt(x: Int): Int =
      round(Math.sqrt(x)).toInt

  }

  implicit object DoubleNumber extends Number[Double] {

    override def plus(x: Double, y: Double): Double =
      x + y

    override def minus(x: Double, y: Double): Double =
      x - y

    override def multiply(x: Double, y: Double): Double =
      x * y

    override def divide(x: Double, y: Int): Double =
      x / y

    override def sqrt(x: Double): Double =
      Math.sqrt(x)

  }

}
