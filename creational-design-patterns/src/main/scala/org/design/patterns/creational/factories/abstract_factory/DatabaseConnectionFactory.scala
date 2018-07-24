package org.design.patterns.creational.factories.abstract_factory

import org.design.patterns.creational.factories.{SimpleConnection, SimpleMySqlConnection, SimplePgSqlConnection}

trait DatabaseConnectionFactory {

  def connect(): SimpleConnection

}

class MySqlFactory extends DatabaseConnectionFactory {

  override def connect(): SimpleConnection =
    new SimpleMySqlConnection

}

class PgSqlFactory extends DatabaseConnectionFactory {

  override def connect(): SimpleConnection =
    new SimplePgSqlConnection

}
