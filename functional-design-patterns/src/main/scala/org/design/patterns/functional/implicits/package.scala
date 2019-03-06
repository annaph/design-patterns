package org.design.patterns.functional

package object implicits {

  implicit def doubleToInt(x: Double): Int =
    Math.round(x).toInt

  implicit def intsToString(ints: List[Int]): String =
    ints.map(_.toChar).mkString

}
