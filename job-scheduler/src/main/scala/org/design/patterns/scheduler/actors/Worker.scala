package org.design.patterns.scheduler.actors

import akka.actor.Actor
import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.scheduler.actors.messages.{Done, Work}
import org.design.patterns.scheduler.config.job.{Console, Sql}
import org.design.patterns.scheduler.dao.DaoService

import scala.sys.process._
import scala.util.{Failure, Success, Try}

class Worker(daoService: DaoService) extends Actor with LazyLogging {

  override def receive: Receive = {
    case Work(name, command, Console) =>
      sender ! doConsoleWork(name, command)
    case Work(name, command, Sql) =>
      sender ! doSqlWork(name, command)
  }

  private def doConsoleWork(workName: String, workCommand: String): Done = {
    val result = workCommand.!
    Done(workName, workCommand, Console, result == 0)
  }

  private def doSqlWork(workName: String, workCommand: String): Done = {
    val connection = daoService.obtainConnection()

    Try {
      val statement = connection prepareStatement workCommand
      daoService.executeSelect(statement) { rs =>
        val numOfColumns = rs.getMetaData.getColumnCount
        daoService.readResultSet(rs) { row =>
          (1 to numOfColumns).map(row.getObject).mkString("\t")
        }
      }
    } match {
      case Success(result) =>
        logger info "Sql query results: "
        result.foreach(r => logger.info(r))

        connection.close()
        Done(workName, workCommand, Sql, true)
      case Failure(e) =>
        connection.close()
        Done(workName, workCommand, Sql, false)
    }
  }

}
