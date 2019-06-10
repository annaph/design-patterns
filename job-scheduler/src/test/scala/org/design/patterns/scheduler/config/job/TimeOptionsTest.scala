package org.design.patterns.scheduler.config.job

import java.time.LocalDateTime

import org.scalatest.{FlatSpec, Matchers}

class TimeOptionsTest extends FlatSpec with Matchers {

  "getInitialDelay with Hourly frequency" should "calculate initial delay when time options are less than an hour " +
    "after now." in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val later = now plusMinutes 20

    val timeOptions = TimeOptions(later.getHour, later.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toMinutes should be(20)
  }

  it should "calculate initial delay when time options are more than an hour after now." in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val later = now plusHours 3

    val timeOptions = TimeOptions(later.getHour, later.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toHours should be(3)
  }

  it should "calculate initial delay when time options are 20 minutes after now and now is 23:50." in {
    // given
    val now = LocalDateTime.now().withHour(23).withMinute(50)
    val later = now plusMinutes 20

    val timeOptions = TimeOptions(later.getHour, later.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toMinutes should be(20)
  }

  it should "calculate initial delay when time options are less than an hour before now." in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val earlier = now minusMinutes 20

    val timeOptions = TimeOptions(earlier.getHour, earlier.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toMinutes should be(40)
  }

  it should "calculate initial delay when time options are more than an hour before now." in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val earlier = now.minusHours(3).minusMinutes(20)

    val timeOptions = TimeOptions(earlier.getHour, earlier.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toMinutes should be(40)
  }

  it should "calculate initial delay when time options are 20 minutes before now and now is 00:10." in {
    // given
    val now = LocalDateTime.now().withHour(0).withMinute(10)
    val earlier = now minusMinutes 20

    val timeOptions = TimeOptions(earlier.getHour, earlier.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Hourly)

    // then
    actual.toMinutes should be(22 * 60 + 50 + 50)
  }

  "getInitialDelay with Daily frequency" should "calculate initial delay when time options are after now" in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val later = now plusMinutes 20

    val timeOptions = TimeOptions(later.getHour, later.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Daily)

    // then
    actual.toMinutes should be(20)
  }

  it should "calculate initial delay when time options are before now" in {
    // given
    val now = LocalDateTime.now().withHour(13).withMinute(0)
    val earlier = now minusMinutes 20

    val timeOptions = TimeOptions(earlier.getHour, earlier.getMinute)

    // when
    val actual = timeOptions getInitialDelay(now, Daily)

    // then
    actual.toMinutes should be(24 * 60 - 20)
  }

}
