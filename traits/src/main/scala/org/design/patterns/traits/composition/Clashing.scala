package org.design.patterns.traits.composition

trait A {

  def hello(): String =
    "Hello, I am trait A!"

  def pass(a: Int): String =
    s"Trait A said: 'You passed $a.'"

}

trait B {

  def hello(): String =
    "Hello, I am trait B!"

}

object Clashing extends App with A with B {

  override def hello(): String =
    super[A].hello()

  def helloB(): String =
    super[B].hello()

  System.out.println(hello())
  System.out.println(helloB())

}
