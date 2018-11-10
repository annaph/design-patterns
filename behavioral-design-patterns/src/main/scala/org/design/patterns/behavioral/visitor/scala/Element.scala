package org.design.patterns.behavioral.visitor.scala

abstract class Element(text: String) {

  def accept(visitor: Visitor): Unit

}

case class Title(text: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

case class Text(text: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

case class Hyperlink(text: String, url: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

class Document(parts: List[Element]) {

  def accept(visitor: Visitor): Unit =
    parts.foreach(_ accept visitor)

}
