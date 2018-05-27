package org.design.patterns.traits.composition

import org.design.patterns.traits.common.{Alarm, Notifier}
import org.design.patterns.traits.composition.self_types.AlarmNotifier

class Watch(brand: String, initialTime: Long) {

  def getTime: Long =
    System.currentTimeMillis - initialTime

}

object WatchUser extends App {

  val expensiveWatch = new Watch("expensive brand", 1000L)
    with Alarm with Notifier {

    override val notificationMessage = "Alarm is running!"

    override def trigger(): String =
      "The alarm was triggered."

    override def clear(): Unit =
      System.out.println("Alarm cleared.")

  }

  val cheapWatch = new Watch("cheap brand", 1000L)
    with Alarm {

    override def trigger(): String = "The alarm was triggered."

  }

  System.out.println(expensiveWatch.trigger())
  expensiveWatch.printNotification()
  System.out.println(s"The time is ${expensiveWatch.getTime}.")
  expensiveWatch.clear()

  System.out.println(cheapWatch.trigger())
  System.out.println("Cheap watches cannot manually stop the alarm...")

}

/**
  * object ReallyExpensiveWatchUser extends App {
  * *
  * val reallyExpensiveWatch = new Watch("really expensive watch", 1000L)
  * with ConnectorWithHelper {
  * *
  * def connect(): Unit =
  *System.out.println("Connected with another connector.")
  * *
  * def close(): Unit =
  *System.out.println("Closed with another connector.")
  * *
  * }
  * *
  *System.out.println("Using the really expensive watch.")
  *reallyExpensiveWatch.findDriver()
  *reallyExpensiveWatch.connect()
  *reallyExpensiveWatch.close()
  * *
  * }**/

object SelfTypeWatchUser extends App {

  //val watch = new Watch("alarm with notification", 1000L) with AlarmNotifier { }

  val watch = new Watch("alarm with notification", 1000L)
    with AlarmNotifier with Notifier {

    override val notificationMessage = "The notification."

    override def trigger(): String =
      "Alarm triggered."

    override def clear(): Unit =
      System.out.println("Alarm cleared.")

  }

  System.out.println(watch.trigger())
  watch.printNotification()
  System.out.println(s"The time is ${watch.getTime}.")
  watch.clear()
}
