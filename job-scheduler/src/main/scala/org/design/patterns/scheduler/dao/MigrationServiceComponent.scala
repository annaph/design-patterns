package org.design.patterns.scheduler.dao

import java.sql.Connection

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

trait MigrationServiceComponent {
  this: DatabaseServiceComponent =>

  val migrationService: MigrationService

  class MigrationService extends LazyLogging {
    type Person = (Int, String, Int)
    type Class = (Int, String)

    def runMigrations(): Unit = {

      val connection = databaseService.obtainConnection()

      Try {
        createPeopleTable(connection)
        createClassesTable(connection)
        createPeopleToClassesTable(connection)

        insertPeople(
          connection,
          List(
            (1, "Anna", 33),
            (2, "Maria", 25),
            (3, "John", 27)))

        insertClasses(
          connection,
          List(
            (1, "Scala Design Patterns"),
            (2, "Java Programming"),
            (3, "Mountain Biking")))

        signPeopleToClasses(
          connection,
          List(
            1 -> 1,
            1 -> 2,
            1 -> 3,
            2 -> 1,
            3 -> 1,
            3 -> 3))
      } match {
        case Success(_) =>
          connection.close()
          logger info "Finish creating and populating database."
        case Failure(e) =>
          connection.close()
          throw new Exception("Error creating and populating database!", e)
      }
    }

    def cleanupDatabase(): Unit = {
      val connection = databaseService.obtainConnection()

      Try {
        dropPeopleToClassesTable(connection)
        dropClassesTable(connection)
        dropPeopleTable(connection)
      } match {
        case Success(_) =>
          connection.close()
          logger info "Finish dropping tables."
        case Failure(e) =>
          connection.close()
          throw new Exception("Error dropping tables!", e)
      }
    }

    private def createPeopleTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE people(
          | id INT PRIMARY KEY,
          | name VARCHAR(255) NOT NULL,
          | age INT NOT NULL
          |)
        """.stripMargin
      )

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'people' created."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error creating table 'people'!", e)
      }
    }

    private def createClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE classes (
          | id INT PRIMARY KEY,
          | name VARCHAR(255) NOT NULL
          |)
        """.stripMargin
      )

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'classes' created."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error creating table 'classes'!", e)
      }
    }

    private def createPeopleToClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE people_classes(
          | person_id INT NOT NULL,
          | class_id INT NOT NULL,
          | PRIMARY KEY(person_id, class_id),
          | FOREIGN KEY(person_id) REFERENCES people(id) ON DELETE CASCADE ON UPDATE CASCADE,
          | FOREIGN KEY(class_id) REFERENCES classes(id) ON DELETE CASCADE ON UPDATE CASCADE
          |)
        """.stripMargin
      )

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'people_class' created."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error creating table 'people_class'!", e)
      }
    }

    private def insertPeople(connection: Connection, people: List[Person]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO people(id, name, age) VALUES(?, ?, ?)")

      Try {
        people.foreach {
          case (id, name, age) =>
            statement setInt(1, id)
            statement setString(2, name)
            statement setInt(3, age)

            statement.addBatch()
        }
        statement.executeBatch()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Inserting people finished with success."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error inserting people!", e)
      }
    }

    private def insertClasses(connection: Connection, classes: List[Class]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO classes(id, name) VALUES (?, ?)"
      )

      Try {
        classes.foreach {
          case (id, name) =>
            statement setInt(1, id)
            statement setString(2, name)

            statement.addBatch()
        }
        statement.executeBatch()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Inserting classes finished with success."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error inserting classes!", e)
      }
    }

    private def signPeopleToClasses(connection: Connection, peopleToClasses: List[(Int, Int)]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO people_classes(person_id, class_id) VALUES (?, ?)"
      )

      Try {
        peopleToClasses.foreach {
          case (personId, classId) =>
            statement setInt(1, personId)
            statement setInt(2, classId)

            statement.addBatch()
        }
        statement.executeBatch()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Assign people to classes finished with success."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error assigning people to classes!", e)
      }
    }

    private def dropPeopleTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        "DROP TABLE people")

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'people' droped."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error dropping table 'people'!", e)
      }
    }

    private def dropClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        "DROP TABLE classes")

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'classes' droped."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error dropping table 'classes'!", e)
      }
    }

    private def dropPeopleToClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        "DROP TABLE people_classes")

      Try {
        statement.executeUpdate()
      } match {
        case Success(_) =>
          statement.close()
          logger info "Table 'people_classes' dropped."
        case Failure(e) =>
          statement.close()
          throw new Exception("Error dropping table 'people_classes'!", e)
      }
    }

  }

}
