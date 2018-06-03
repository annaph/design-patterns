package org.design.patterns.unification.adts

sealed case class RGB(red: Int, green: Int, blue: Int)

object RGBDemo extends App {

  val magneta = RGB(255, 0, 255)
  System.out.println(s"Magneta in RGB is: $magneta")

}
