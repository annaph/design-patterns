package org.design.patterns.behavioral.observer

import scala.collection.mutable

trait Observer[T] {

  def handleUpdate(subject: T): Unit

}

trait Observable[T] {
  this: T =>

  private val observers = mutable.ListBuffer.empty[Observer[T]]

  def addObserver(observer: Observer[T]): Unit =
    observer +=: observers

  def notifyObservers(): Unit =
    observers.foreach(_.handleUpdate(this))

}
