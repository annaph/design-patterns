package org.design.patterns.creational.factories.abstract_factory

class DatabaseClient(connectionFactory: DatabaseConnectionFactory) {

  def executeQuery(query: String): Unit = {
    val connection = connectionFactory.connect()
    connection executeQuery query
  }

}
