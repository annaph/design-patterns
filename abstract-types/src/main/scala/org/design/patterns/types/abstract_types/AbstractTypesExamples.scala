package org.design.patterns.types.abstract_types

trait ContainerAT {
  type T

  val data: T

  def compare(other: T): Boolean =
    data equals other

}

class StringContainer(val data: String) extends ContainerAT {
  type T = String
}

object AbstractTypesExamples extends App {

  val stringContainer = new StringContainer("some text")

  System.out.println(s"Comparing with string: ${stringContainer compare "some text"}")

}
