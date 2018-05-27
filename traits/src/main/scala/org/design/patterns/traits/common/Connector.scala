package org.design.patterns.traits.common

abstract class Connector {

  def connect(): Unit

  def close(): Unit

}

trait ConnectorWithHelper extends Connector {

  def findDriver(): Unit =
    System.out.println("Find driver called.")

}

class PgSqlConnector extends ConnectorWithHelper {

  override def connect(): Unit =
    System.out.println("Connected...")

  override def close(): Unit =
    System.out.println("Closed...")

}
