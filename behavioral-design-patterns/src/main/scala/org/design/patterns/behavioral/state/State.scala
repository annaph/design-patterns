package org.design.patterns.behavioral.state

import org.design.patterns.behavioral.state.model.MediaPlayer

trait State[T] {

  def press(context: T): Unit

}

class Playing extends State[MediaPlayer] {

  override def press(context: MediaPlayer): Unit = {
    System.out.println("Pressing pause.")
    context setState new Paused
  }

}

class Paused extends State[MediaPlayer] {

  override def press(context: MediaPlayer): Unit = {
    System.out.println("Pressing play.")
    context setState new Playing
  }

}
