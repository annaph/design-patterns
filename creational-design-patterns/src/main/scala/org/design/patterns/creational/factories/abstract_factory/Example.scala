package org.design.patterns.creational.factories.abstract_factory

object Example extends App {

  val mySqlClient = new DatabaseClient(new MySqlFactory)
  val pgSqlClient = new DatabaseClient(new PgSqlFactory)

  mySqlClient executeQuery "SELECT * FROM users"
  pgSqlClient executeQuery "SELECT * FROM employees"

}
