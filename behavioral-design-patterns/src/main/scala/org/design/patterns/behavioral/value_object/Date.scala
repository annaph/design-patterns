package org.design.patterns.behavioral.value_object

case class Date(
  day: Int,
  month: String,
  year: Int
)

object DateExample extends App {

  val thirdOfMarch = Date(3, "MARCH", 2018)
  val fourthOfJuly = Date(4, "JULY", 2018)

  val newYear1 = Date(1, "DECEMBER", 2017)
  val newYear2 = Date(1, "DECEMBER", 2017)

  System.out.println(s"The 3rd of March 2019 is the same as the 4th of July 2019: ${thirdOfMarch == fourthOfJuly}")
  System.out.println(s"The new year of 2017 is here twice: ${newYear1 == newYear2}")

}

class BadDate(
  day: Int,
  month: String,
  year: Int)

object BadDateExample extends App {

  val thirdOfMarch = new BadDate(3, "MARCH", 2018)
  val fourthOfJuly = new BadDate(4, "JULY", 2018)

  val newYear1 = new BadDate(1, "DECEMBER", 2017)
  val newYear2 = new BadDate(1, "DECEMBER", 2017)

  System.out.println(s"The 3rd of March 2019 is the same as the 4th of July 2019: ${thirdOfMarch == fourthOfJuly}")
  System.out.println(s"The new year of 2017 is here twice: ${newYear1 == newYear2}")

}
