package org.design.patterns.functional.cake

import java.sql.Connection

import org.design.patterns.functional.cake.model.{Class, Person}

import scala.util.{Failure, Success, Try}

trait MigrationComponent {
  this: DatabaseComponent =>

  val migrationService: MigrationService

  class MigrationService {

    def runMigrations(): Unit = {
      val connection = databaseService.getConnection

      Try {
        createPeopleTable(connection)
        createClassesTable(connection)
        createPeopleToClassesTable(connection)

        insertPeople(
          connection,
          List(
            Person(1, "Anna", 33),
            Person(2, "Maria", 25),
            Person(3, "John", 27)))

        insertClasses(
          connection,
          List(
            Class(1, "Scala Design Patterns"),
            Class(2, "Java Programming"),
            Class(3, "Mountain Biking")))

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
          System.out.println("Finish creating and populating database.")
        case Failure(e) =>
          connection.close()
          throw new RuntimeException("Error creating and populating database!", e)
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
          System.out.println("Table 'people' created.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error creating table 'people'!", e)
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
          System.out.println("Table 'classes' created.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error creating table 'classes'!", e)
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
          System.out.println("Table 'people_class' created.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error creating table 'people_class'!", e)
      }
    }

    private def insertPeople(connection: Connection, people: List[Person]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO people(id, name, age) VALUES(?, ?, ?)")

      Try {
        people.foreach { person =>
          statement setInt(1, person.id)
          statement setString(2, person.name)
          statement setInt(3, person.age)

          statement.addBatch()
        }
        statement.executeBatch()
      } match {
        case Success(_) =>
          statement.close()
          System.out.println("Inserting people finished with success.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error inserting people!", e)
      }
    }

    private def insertClasses(connection: Connection, classes: List[Class]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO classes(id, name) VALUES (?, ?)"
      )

      Try {
        classes.foreach { cls =>
          statement setInt(1, cls.id)
          statement setString(2, cls.name)

          statement.addBatch()
        }
        statement.executeBatch()
      } match {
        case Success(_) =>
          statement.close()
          System.out.println("Inserting classes finished with success.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error inserting classes!", e)
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
          System.out.println("Assign people to classes finished with success.")
        case Failure(e) =>
          statement.close()
          throw new RuntimeException("Error assigning people to classes!", e)
      }
    }

  }

}
