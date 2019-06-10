package org.design.patterns.scheduler.actors.messages

import org.design.patterns.scheduler.config.job.{JobConfig, JobType}

sealed trait SchedulerMessage

case class Schedule(configs: List[JobConfig]) extends SchedulerMessage

case class Work(name: String, command: String, jobType: JobType) extends SchedulerMessage

case class Done(name: String, command: String, jobType: JobType, success: Boolean) extends SchedulerMessage
