package org.design.patterns.behavioral.null_object

import java.util.concurrent.ConcurrentLinkedQueue

import scala.util.Random

class DataGenerator extends Runnable {

  val MAX_VAL = 10
  val MAX_TIME = 10000

  private val queue: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue()
  private var isStop = false

  override def run(): Unit = {
    val random = new Random()
    while (!isStop) {
      Thread sleep random.nextInt(MAX_TIME)
      queue add random.nextInt(MAX_VAL)
    }
  }

  def message(): Option[Message] =
    Option(queue.poll()).map(Message)

  def requestStop(): Unit = this.synchronized {
    isStop = true
  }

}
