package org.design.patterns.behavioral.memento

trait Memento[T] {

  protected val state: T

  def getState: T = state

}

trait Caretaker[T] {

  import scala.collection.mutable

  val states: mutable.ListBuffer[Memento[T]] = mutable.ListBuffer.empty[Memento[T]]

}

trait Originator[T] {

  def createMemento: Memento[T]

  def restore(memento: Memento[T]): Unit

}
