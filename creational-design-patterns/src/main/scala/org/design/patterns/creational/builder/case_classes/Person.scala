package org.design.patterns.creational.builder.case_classes

case class Person(
  firstName: String = "",
  lastName: String = "",
  age: Int = 0)

object PersonCaseClassExample extends App {

  val person1 = Person(
    firstName = "Anna",
    lastName = "Philips",
    age = 27)

  val person2 = Person(
    firstName = "John")

  System.out.println(s"Person 1: $person1")
  System.out.println(s"Person 2: $person2")

}
