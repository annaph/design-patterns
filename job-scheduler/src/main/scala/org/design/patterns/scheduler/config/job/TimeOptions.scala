package org.design.patterns.scheduler.config.job

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.{Duration, FiniteDuration}

case class TimeOptions(hours: Int, minutes: Int) {
  require(hours >= 0 && hours <= 23, s"Hours must be between 0 and 23: $hours")
  require(minutes >= 0 && minutes <= 59, s"Minutes must be between 0 and 59: $minutes")

  def getInitialDelay(now: LocalDateTime, frequency: JobFrequency): FiniteDuration = {
    val firstRun = now.withHour(hours).withMinute(minutes)

    val actualFirstRun = frequency match {
      case Hourly if firstRun isAfter now =>
        firstRun
      case Hourly =>
        Stream.from(1)
          .map(firstRun plusHours _)
          .dropWhile(!_.isAfter(now))
          .head
      case Daily if firstRun isAfter now =>
        firstRun
      case Daily =>
        firstRun plusDays 1
    }

    val secondsUntilRun = now until(actualFirstRun, ChronoUnit.SECONDS)
    Duration create(secondsUntilRun, TimeUnit.SECONDS)
  }

}
