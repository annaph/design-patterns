package org.design.patterns.functional.laziness

case class Person(name: String, age: Int)

object Person {

  def readFromDatabase(): List[Person] = {
    System.out.println("Retrieving people...")
    Thread sleep 3000

    List(
      Person("Anna", 34),
      Person("Maria", 23),
      Person("Nicole", 27)
    )
  }

  def printPeopleBad(people: => List[Person]): Unit = {
    System.out.println(s"Print first time: $people")
    System.out.println(s"Print second time: $people")
  }

  def printPeopleGood(people: => List[Person]): Unit = {
    lazy val peopleCopy = people

    System.out.println(s"Print first time: $peopleCopy")
    System.out.println(s"Print second time: $peopleCopy")
  }

  def printPeopleGood2(people: => List[Person]): Unit = {
    val peopleCopy = (() => people) ()

    System.out.println(s"Print first time: $peopleCopy")
    System.out.println(s"Print second time: $peopleCopy")
  }

}

object Example extends App {

  import Person._

  System.out.println("Now printing bad.")
  printPeopleBad(readFromDatabase())

  System.out.println(s"Now printing good.")
  printPeopleGood(readFromDatabase())

  System.out.println(s"Now printing good again.")
  printPeopleGood2(readFromDatabase())

}
