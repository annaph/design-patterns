package org.design.patterns.behavioral.visitor.scala.better

trait Element {

  def text: String

  def accept(visitor: Element => Unit): Unit =
    visitor(this)

}

case class Title(text: String) extends Element

case class Text(text: String) extends Element

case class Hyperlink(text: String, url: String) extends Element

class Document(parts: List[Element]) {

  def accept(visitor: Element => Unit): Unit =
    parts.foreach(_ accept visitor)

}
