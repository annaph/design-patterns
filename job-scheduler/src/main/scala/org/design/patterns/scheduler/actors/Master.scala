package org.design.patterns.scheduler.actors

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit.{DAYS, HOURS}

import akka.actor.{Actor, Cancellable, Props}
import akka.routing.RoundRobinPool
import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.scheduler.actors.messages.{Done, Schedule, Work}
import org.design.patterns.scheduler.config.job.{Daily, Hourly, JobConfig}

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class Master(numbersOfWorkers: Int, actorFactory: ActorFactory) extends Actor with LazyLogging {

  private val router = context.actorOf(
    Props(actorFactory.createWorkerActor()).withRouter(RoundRobinPool(numbersOfWorkers)),
    "scheduler-master-worker-router")

  private val scheduledJobs = ListBuffer.empty[Cancellable]

  override def receive: Receive = {
    case Schedule(configs) =>
      configs.foreach(scheduleJobConfig)
    case done: Done =>
      logDoneJob(done)
  }

  private def scheduleJobConfig(config: JobConfig): Unit = {
    val scheduledJob = context.system.scheduler.schedule(
      config.timeOptions.getInitialDelay(LocalDateTime.now, config.frequency),
      config.frequency match {
        case Hourly =>
          Duration create(1, HOURS)
        case Daily =>
          Duration create(1, DAYS)
      },
      router,
      Work(config.name, config.command, config.jobType)
    )

    scheduledJob +: scheduledJobs
    logger info("Scheduled: {}", config)
  }

  private def logDoneJob(done: Done): Unit =
    if (done.success)
      logger info("Successfully completed {} ({}).", done.name, done.command)
    else
      logger error("Failure! Command {} ({}) returned a non-zero result code.", done.name, done.command)

  override def postStop(): Unit =
    scheduledJobs.foreach(_.cancel())

}
