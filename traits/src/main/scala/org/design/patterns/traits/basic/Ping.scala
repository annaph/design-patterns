package org.design.patterns.traits.basic

trait Ping {

  def ping(): Unit =
    System.out.println("ping")

}

trait Pong {

  def pong(): Unit =
    System.out.println("pong")
}

trait PingPong extends Ping with Pong {

  def pingPong(): Unit = {
    ping()
    pong()
  }

}

object Runner extends App with PingPong {

  pingPong()

}

object MixinRunner extends App with Ping with Pong {

  ping()
  pong()

}
