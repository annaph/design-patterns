package org.design.patterns.structural.flyweight

import scala.collection.mutable

class Circle(color: Color) {
  System.out.println(s"Creating a circle with $color color.")

  override def toString: String =
    s"Circle($color)"

}

object Circle {

  private val cache = mutable.Map.empty[Color, Circle]

  def apply(color: Color): Circle =
    cache getOrElseUpdate(color, new Circle(color))

  def circlesCreated: Int =
    cache.size

}

class Graphic {

  private val items = mutable.ListBuffer.empty[(Int, Int, Double, Circle)]

  def addCircle(x: Int, y: Int, radius: Double, circle: Circle): Unit =
    items += ((x, y, radius, circle))

  def draw(): Unit =
    items.foreach {
      case (x, y, radius, circle) =>
        System.out.println(s"Drawing a circle at ($x, $y) with radius $radius: $circle")
    }

}

object FlyweightExample extends App {

  val graphic = new Graphic

  graphic addCircle(1, 1, 1.0, Circle(Green))
  graphic addCircle(1, 2, 1.0, Circle(Red))

  graphic addCircle(2, 1, 1.0, Circle(Blue))
  graphic addCircle(2, 2, 1.0, Circle(Green))

  graphic addCircle(2, 3, 1.0, Circle(Yellow))
  graphic addCircle(3, 2, 1.0, Circle(Magneta))
  graphic addCircle(3, 3, 1.0, Circle(Blue))

  graphic addCircle(4, 3, 1.0, Circle(Blue))
  graphic addCircle(3, 4, 1.0, Circle(Yellow))
  graphic addCircle(4, 4, 1.0, Circle(Red))

  graphic.draw()

  System.out.println(s"Total number of circle objects created: ${Circle.circlesCreated}")

}
