package org.design.patterns.behavioral.state.model

import org.design.patterns.behavioral.state.{Paused, State}

case class MediaPlayer() {

  private var _state: State[MediaPlayer] = new Paused

  def pressPlayOrPauseButton(): Unit =
    _state press this

  def setState(state: State[MediaPlayer]): Unit =
    _state = state

}

object MediaPlayerExample extends App {

  val player = MediaPlayer()

  player.pressPlayOrPauseButton()
  player.pressPlayOrPauseButton()
  player.pressPlayOrPauseButton()
  player.pressPlayOrPauseButton()

}
