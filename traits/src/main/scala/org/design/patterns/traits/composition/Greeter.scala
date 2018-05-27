package org.design.patterns.traits.composition

trait FormalGreeting {

  def hello(): String

}

trait InformalGreeting {

  def hello(): String

}

class Greeter extends FormalGreeting with InformalGreeting {

  override def hello(): String =
    "Good morning, sir/madam!"

}

object GreeterUser extends App {
  val greeter = new Greeter()

  System.out.println(greeter.hello())
}
