package org.design.patterns.creational.factories

trait SimpleConnection {

  def name: String

  def executeQuery(query: String): Unit

}

class SimpleMySqlConnection extends SimpleConnection {

  override def name: String =
    "SimpleMySqlConnection"

  override def executeQuery(query: String): Unit =
    System.out.println(s"Executing the '${query}' the MySQL way.")

}

class SimplePgSqlConnection extends SimpleConnection {

  override def name: String =
    "SimplePgSqlConnection"

  override def executeQuery(query: String): Unit =
    System.out.println(s"Executing the '${query}' the PgSQL way.")

}
