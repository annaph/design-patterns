package org.design.patterns.unification.adts

import java.lang.Math.{PI, pow}

case class Point(x: Int, y: Double)

sealed trait Shape

case class Circle(centre: Point, radius: Double) extends Shape

case class Rectangle(topLeft: Point, height: Double, width: Double) extends Shape

object Shape {

  def area(shape: Shape): Double =
    shape match {
      case Circle(Point(x, y), radius) =>
        PI * pow(radius, 2)
      case Rectangle(_, h, w) =>
        h * w
    }

}

object ShapeDemo extends App {

  import Shape.area

  val circle = Circle(Point(1, 2), 2.5)
  val rectangle = Rectangle(Point(6, 7), 5, 6)

  System.out.println(s"The circle area is: ${area(circle)}")
  System.out.println(s"The rectangle is: ${area(rectangle)}")

}
