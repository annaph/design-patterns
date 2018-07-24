package org.design.patterns.creational.factories.factory_method

import org.design.patterns.creational.factories.{SimpleConnection, SimpleMySqlConnection, SimplePgSqlConnection}

abstract class BadDatabaseClient {

  def executeQuery(query: String): Unit = {
    val connection = connect()

    connectionPrinter printSimpleConnection connection
    connection executeQuery query
  }

  protected def connect(): SimpleConnection

  protected def connectionPrinter: SimpleConnectionPrinter

}

class BadMySqlClient extends BadDatabaseClient {

  override protected def connect(): SimpleConnection =
    new SimpleMySqlConnection

  override protected def connectionPrinter: SimpleConnectionPrinter =
    new SimpleMySqlConnectionPrinter

}

class BadPgSqlClient extends BadDatabaseClient {

  override protected def connect(): SimpleConnection =
    new SimplePgSqlConnection

  override protected def connectionPrinter: SimpleConnectionPrinter =
    new SimpleMySqlConnectionPrinter

}
