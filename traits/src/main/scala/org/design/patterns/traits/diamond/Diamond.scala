package org.design.patterns.traits.diamond

;

trait A {

  def hello(): String =
    "Hello from A"

}

trait B extends A {

  override def hello(): String =
    "Hello from B"

}

trait C extends A {

  override def hello(): String =
    "Hello from C"

}

trait D extends B with C {

}

object Diamond extends App with D {

  System.out.println(hello())

}
