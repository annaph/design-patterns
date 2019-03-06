package org.design.patterns.functional.cake

import java.sql.{PreparedStatement, ResultSet}

import org.design.patterns.functional.cake.model.{Class, Person}

import scala.util.{Failure, Success, Try}

trait DaoComponent {
  this: DatabaseComponent =>

  val daoService: DaoService

  class DaoService {

    def getPeople: List[Person] = {
      val connection = databaseService.getConnection

      Try {
        executeSelect(connection prepareStatement "SELECT id, name, age FROM people") { rs =>
          readResultSet(rs) { row =>
            Person(row.getInt(1), row.getString(2), row.getInt(3))
          }
        }
      } match {
        case Success(people) =>
          connection.close()
          people
        case Failure(e) =>
          connection.close()
          throw new Exception("Error reading from 'people' table!", e)
      }
    }

    def getClasses: List[Class] = {
      val connection = databaseService.getConnection

      Try {
        executeSelect(connection prepareStatement "SELECT id, name FROM classes") { rs =>
          readResultSet(rs) { row =>
            Class(row.getInt(1), row.getString(2))
          }
        }
      } match {
        case Success(classes) =>
          connection.close()
          classes
        case Failure(e) =>
          connection.close()
          throw new Exception("Error reading from 'classes' table", e)
      }
    }

    def getPeopleInClass(className: String): List[Person] = {
      val connection = databaseService.getConnection

      Try {
        val statement = connection.prepareStatement(
          """
            |SELECT p.id, p.name, p.age
            |FROM people p
            |INNER JOIN people_classes pc ON pc.person_id = p.id
            |INNER JOIN classes c ON c.id = pc.class_id
            |WHERE c.name = ?
          """.stripMargin)

        statement setString(1, className)

        executeSelect(statement) { rs =>
          readResultSet(rs) { row =>
            Person(row.getInt(1), row.getString(2), row.getInt(3))
          }
        }
      } match {
        case Success(people) =>
          connection.close()
          people
        case Failure(e) =>
          connection.close()
          throw new Exception("Error reading from 'people' and 'class' tables", e)
      }
    }

    private def executeSelect[T](preparedStatement: PreparedStatement)(f: ResultSet => List[T]): List[T] = {
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

    private def readResultSet[T](rs: ResultSet)(f: ResultSet => T): List[T] = {
      Iterator.continually(rs.next() -> rs).takeWhile(_._1).map {
        case (_, row) =>
          f(row)
      }.toList
    }

  }

}
