package org.design.patterns.creational.factories.factory_method

import org.design.patterns.creational.factories.{SimpleConnection, SimpleMySqlConnection, SimplePgSqlConnection}

abstract class DatabaseClient {

  def executeQuery(query: String): Unit = {
    val connection = connect()
    connection executeQuery query
  }

  protected def connect(): SimpleConnection

}

class MySqlClient extends DatabaseClient {

  override protected def connect(): SimpleConnection =
    new SimpleMySqlConnection

}

class PgSqlClient extends DatabaseClient {

  override protected def connect(): SimpleConnection =
    new SimplePgSqlConnection()

}
