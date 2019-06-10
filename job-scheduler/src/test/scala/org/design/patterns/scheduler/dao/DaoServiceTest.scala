package org.design.patterns.scheduler.dao

import java.sql.{Connection, ResultSet}

import org.design.patterns.scheduler.TestEnvironment
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class DaoServiceTest
  extends FlatSpec
    with Matchers
    with BeforeAndAfter
    with TestEnvironment {

  override val databaseService = new H2DatabaseService

  override val migrationService = new MigrationService

  override val daoService = new DaoServiceImpl

  private var _connection: Connection = _

  before {
    migrationService.runMigrations()
    _connection = daoService.obtainConnection()
  }

  after {
    migrationService.cleanupDatabase()
    _connection.close()
  }

  "readResultSet" should "properly iterate over a result set and apply a function to it." in {
    // given
    val statement = _connection prepareStatement "SELECT name FROM people"
    val readRowFunc: ResultSet => String = _ getString "name"

    // when
    val actual = daoService.executeSelect(statement)(daoService.readResultSet(_)(readRowFunc))

    // then
    actual should have size 3
    actual should contain("Anna")
    actual should contain("Maria")
    actual should contain("John")
  }

}
