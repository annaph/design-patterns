package org.design.patterns.creational.builder.java_way

class Person(builder: PersonBuilder) {

  val firstName: String = builder.firstName
  val lastName: String = builder.lastName
  val age: Int = builder.age

}

class PersonBuilder {

  var firstName = ""
  var lastName = ""
  var age = 0

  def setFirstName(firstName: String): PersonBuilder = {
    this.firstName = firstName
    this
  }

  def setLastName(lastName: String): PersonBuilder = {
    this.lastName = lastName
    this
  }

  def setAge(age: Int): PersonBuilder = {
    this.age = age
    this
  }

  def build(): Person =
    new Person(this)

}

object PersonBuilderExample extends App {

  val person: Person = new PersonBuilder()
    .setFirstName("Anna")
    .setLastName("Philips")
    .setAge(27)
    .build()

  System.out.println(s"Person: ${person.firstName} ${person.lastName}. Age: ${person.age}.")

}
