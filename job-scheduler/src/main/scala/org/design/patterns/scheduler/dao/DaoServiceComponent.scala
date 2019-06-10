package org.design.patterns.scheduler.dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import scala.util.{Failure, Success, Try}

trait DaoService {

  def obtainConnection(): Connection

  def executeSelect[T](preparedStatement: PreparedStatement)(f: ResultSet => List[T]): List[T] = {
    Try {
      f(preparedStatement.executeQuery())
    } match {
      case Success(result) =>
        preparedStatement.close()
        result
      case Failure(e) =>
        preparedStatement.close()
        throw new Exception("Error executing query!", e)
    }
  }

  def readResultSet[T](rs: ResultSet)(f: ResultSet => T): List[T] = {
    Iterator.continually(rs.next() -> rs).takeWhile(_._1).map {
      case (_, row) =>
        f(row)
    }.toList
  }

}

trait DaoServiceComponent {
  this: DatabaseServiceComponent =>

  val daoService: DaoService

  class DaoServiceImpl extends DaoService {

    override def obtainConnection(): Connection =
      databaseService.obtainConnection()

  }

}
