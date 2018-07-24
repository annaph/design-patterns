package org.design.patterns.creational.builder.type_safe

sealed trait BuildStep

sealed trait HasFirstName extends BuildStep

sealed trait HasLastName extends BuildStep

class Person(
  val firstName: String,
  val lastName: String,
  val age: Int)

class PersonBuilder[PassedStep <: BuildStep] private(
  var firstName: String,
  var lastName: String,
  var age: Int) {

  protected def this() =
    this("", "", 0)

  protected def this(pb: PersonBuilder[_]) =
    this(pb.firstName, pb.lastName, pb.age)

  def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
    this.firstName = firstName
    new PersonBuilder[HasFirstName](this)
  }

  def setLastName(lastName: String)(implicit ev: PassedStep =:= HasFirstName): PersonBuilder[HasLastName] = {
    this.lastName = lastName
    new PersonBuilder[HasLastName](this)
  }

  def setAge(age: Int): PersonBuilder[PassedStep] = {
    this.age = age
    this
  }

  def build()(implicit ev: PassedStep =:= HasLastName): Person =
    new Person(firstName, lastName, age)

}

object PersonBuilder {

  def apply(): PersonBuilder[BuildStep] =
    new PersonBuilder()

}

object PersonBuilderTypeSafeExample extends App {

  val person: Person = PersonBuilder()
    .setFirstName("Anna")
    .setLastName("Philips")
    .setAge(27)
    .build()

  System.out.println(s"Person: ${person.firstName} ${person.lastName}. Age: ${person.age}.")

}
