package org.design.patterns.creational.factories.factory_method

object BadExample extends App {

  val mySqlClient: BadDatabaseClient = new BadMySqlClient
  val pgSqlClient: BadDatabaseClient = new BadPgSqlClient

  mySqlClient executeQuery "SELECT * FROM users"
  pgSqlClient executeQuery "SELECT * FROM employees"

}
