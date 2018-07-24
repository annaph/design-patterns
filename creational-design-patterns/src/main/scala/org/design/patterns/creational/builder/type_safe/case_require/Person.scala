package org.design.patterns.creational.builder.type_safe.case_require

case class Person(
  firstName: String = "",
  lastName: String = "",
  age: Int = 0
) {
  require(firstName != "", "First name is required.")
  require(lastName != "", "Last name is required.")
}

object PersonCaseClassRequireExample extends App {

  val person1 = Person("Anna", "Philips", 27)
  System.out.println(s"Person 1: $person1")

  try {
    val person2 = Person("John")
    System.out.println(s"Person 2: $person2")
  } catch {
    case e: Throwable =>
      e.printStackTrace()
  }

}
