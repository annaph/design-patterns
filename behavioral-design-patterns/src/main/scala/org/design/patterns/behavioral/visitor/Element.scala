package org.design.patterns.behavioral.visitor

abstract class Element(val text: String) {

  def accept(visitor: Visitor): Unit

}

class Title(text: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

class Text(text: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

class Hyperlink(text: String, val url: String) extends Element(text) {

  override def accept(visitor: Visitor): Unit =
    visitor visit this

}

class Document(parts: List[Element]) {

  def accept(visitor: Visitor): Unit =
    parts.foreach(_ accept visitor)

}
