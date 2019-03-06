package org.design.patterns.functional.partial_functions

object PartiallyDefinedFunctions {

  val squareRoot: PartialFunction[Int, Double] = {
    case x if x >= 0 =>
      Math.sqrt(x)
  }

  val square: PartialFunction[Int, Double] = {
    case x if x < 0 =>
      Math.pow(x, 2)
  }

}

object PartiallyDefinedExample extends App {

  import PartiallyDefinedFunctions._

  val items = List(-1, 10, 11, -36, 36, -49, 49, 81)

  System.out.println(s"Can we calculate a root for -10: ${squareRoot isDefinedAt -10}")
  System.out.println(s"Square roots: ${items collect squareRoot}")
  System.out.println(s"Square roots or squares: ${items collect (squareRoot orElse square)}")

}
