package org.design.patterns.unification.modules

trait Tick {

  def ticker: Ticker

  trait Ticker {

    def count(): Int

    def tick(): Unit

  }

}

trait TickUser extends Tick {

  class TickUserImpl extends Ticker {
    var curr = 0

    override def count(): Int =
      curr

    def tick(): Unit =
      curr = curr + 1

  }

  object ticker extends TickUserImpl

}

trait Alarm {

  def alarm: Alarmer

  trait Alarmer {

    def trigger(): Unit

  }

}

trait AlarmUser extends Alarm with Tick {

  class AlarmUserImpl extends Alarmer {

    override def trigger(): Unit =
      if (ticker.count() % 10 == 0) {
        System.out.println(s"Alarm triggered at ${ticker.count()}!")
      }

  }

  object alarm extends AlarmUserImpl

}

object ModuleDemo extends App with AlarmUser with TickUser {

  System.out.println("Running the ticker. Should trigger the alarm every 10 times.")

  (1 to 100).foreach { _ =>
    ticker.tick()
    alarm.trigger()
  }

}
