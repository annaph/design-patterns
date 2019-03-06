package org.design.patterns.functional.cake

import java.sql.Connection

import org.h2.jdbcx.JdbcConnectionPool

trait DatabaseService {
  val driver: String
  val connectionString: String
  val username: String
  val password: String

  def getConnection: Connection

}

trait DatabaseComponent {

  val databaseService: DatabaseService

  class H2DatabaseService(val connectionString: String, val username: String, val password: String)
    extends DatabaseService {

    override val driver = "org.h2.driver"

    override def getConnection: Connection =
      JdbcConnectionPool.create(connectionString, username, password).getConnection

  }

}
