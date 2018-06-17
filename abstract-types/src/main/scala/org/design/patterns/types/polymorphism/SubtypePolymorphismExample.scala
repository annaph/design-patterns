package org.design.patterns.types.polymorphism

abstract class Item {

  def pack: String

}

class Fruit extends Item {

  override def pack: String =
    "I'm a fruit and I'm packed in a bag."

}

class Drink extends Item {

  override def pack: String =
    "I'm a drink and I'm packed in a bottle."

}

object SubtypePolymorphismExample extends App {

  val shoppingBasket: List[Item] = List(
    new Fruit,
    new Drink
  )

  shoppingBasket.foreach { item =>
    System.out.println(item.pack)
  }

}
