package org.design.patterns.functional.partial_functions

object PartiallyAppliedFunctions {

  val greaterOrEqual: (Int, Int) => Boolean =
    (a: Int, b: Int) => a >= b

  val lessOrEqual: (Int, Int) => Boolean =
    (a: Int, b: Int) => a <= b

  val greaterOrEqualCurriedVal: Int => Int => Boolean =
    b => a => a >= b

  val lessOrEqualCurriedVal: Int => Int => Boolean =
    b => a => a <= b

  def greaterOrEqualCurried(b: Int)(a: Int): Boolean =
    a >= b

  def lessOrEqualCurried(b: Int)(a: Int): Boolean =
    a <= b

}

object PartiallyAppliedFunctionsExample extends App {

  import PartiallyAppliedFunctions._

  val MIN = 5
  val MAX = 20

  val numbers = List(1, 5, 6, 11, 18, 19, 20, 21, 25, 30)

  // partially applied
  val ge: Int => Boolean = greaterOrEqual(_: Int, MIN)
  val le: Int => Boolean = lessOrEqual(_: Int, MAX)

  // curried
  val geCurried: Int => Boolean = greaterOrEqualCurried(MIN)(_)
  val leCurried: Int => Boolean = lessOrEqualCurried(MAX)(_)

  val geCurried2: Int => Boolean = greaterOrEqualCurriedVal(MIN)
  val leCurried2: Int => Boolean = lessOrEqualCurriedVal(MAX)

  System.out.println(s"Filtered list: ${numbers.filter(i => ge(i) && le(i))}")
  System.out.println(s"Filtered list: ${numbers.filter(i => geCurried(i) && leCurried(i))}")
  System.out.println(s"Filtered list: ${numbers.filter(i => geCurried2(i) && leCurried2(i))}")

}
