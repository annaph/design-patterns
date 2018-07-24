package org.design.patterns.creational.factories.factory_method

object Example extends App {

  val mySqlClient: DatabaseClient = new MySqlClient
  val pgSqlClient: DatabaseClient = new PgSqlClient

  mySqlClient executeQuery "SELECT * FROM users"
  pgSqlClient executeQuery "SELECT * FROM employees"

}
