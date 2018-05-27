package org.design.patterns.traits.basic

trait Beeper {

  def beep(times: Int): Unit = {
    assert(times >= 0)

    1 to times foreach { i =>
      System.out.println(s"Beep number: $i")
    }
  }

}

object BeeperRunner extends App {
  val TIMES = 10

  val beeper = new Beeper {}
  beeper beep TIMES

}
