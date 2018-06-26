package org.design.patterns.components

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.design.patterns.components.base.Time

trait TimeComponent {

  val time: Time

  class TimeImpl extends Time {

    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    override def getTime: String =
      s"The time is: ${LocalDateTime.now().format(formatter)}"

  }

}
