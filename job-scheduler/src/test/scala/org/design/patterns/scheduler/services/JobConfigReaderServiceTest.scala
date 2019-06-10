package org.design.patterns.scheduler.services

import org.design.patterns.scheduler.TestEnvironment
import org.design.patterns.scheduler.config.job.{Console, Hourly, JobConfig, TimeOptions}
import org.scalatest.{FlatSpec, Matchers}

class JobConfigReaderServiceTest
  extends FlatSpec
    with Matchers
    with TestEnvironment {

  override val ioService: IOService = new IOService

  override val jobConfigReaderService: JobConfigReaderService = new JobConfigReaderService

  "readJobConfigs" should "read and parse configurations successfully." in {
    // when
    val actual = jobConfigReaderService.readJobConfigs()

    // then
    actual should have size 1

    actual should contain(
      JobConfig(
        name = "Test Command",
        command = "ping google.com -c 12",
        frequency = Hourly,
        jobType = Console,
        timeOptions = TimeOptions(12, 10)))
  }

}
