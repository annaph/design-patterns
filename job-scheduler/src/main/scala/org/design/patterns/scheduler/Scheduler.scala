package org.design.patterns.scheduler

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.scheduler.actors.messages.Schedule

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Scheduler extends App with LazyLogging {

  import org.design.patterns.scheduler.registry.ComponentRegistry._

  logger info "Running migrations before doing anything else."
  migrationService.runMigrations()
  logger info "Migrations done!"

  val actorSystem = ActorSystem("scheduler")

  val master = actorSystem.actorOf(
    Props(actorFactory.createMasterActor()),
    "scheduler-master")

  sys.addShutdownHook({
    logger info "Awaiting actor system termination."
    Await result(actorSystem.terminate(), Duration.Inf)
    logger info "Actor system terminated. Bye!"
  })

  master ! Schedule(jobConfigReaderService.readJobConfigs())

  logger info "Started! Use CTRL+C to exit."

}
