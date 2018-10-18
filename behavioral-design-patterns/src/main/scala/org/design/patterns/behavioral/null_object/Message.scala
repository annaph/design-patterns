package org.design.patterns.behavioral.null_object

import scala.util.Random

case class Message(number: Int) {

  def print(): String =
    s"This a message with number: $number."

}

object MessageExample extends App {

  val TIMES_TO_TRY = 10
  val MAX_TIME = 5000

  val generator = new DataGenerator
  new Thread(generator).start()

  val random = new Random()
  (0 to TIMES_TO_TRY).foreach { _ =>
    Thread sleep random.nextInt(MAX_TIME)

    System.out.println("Getting next message...")

    generator.message().foreach { msg =>
      System.out.println(msg.print())
    }
  }

  generator.requestStop()

}
