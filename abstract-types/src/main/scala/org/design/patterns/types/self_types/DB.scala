package org.design.patterns.types.self_types

trait DB {

  def connect(): Unit =
    System.out.println("Connected.")

  def dropDatabase(): Unit =
    System.out.println("Dropping!")

  def close(): Unit =
    System.out.println("Closed.")

}

trait UserDB {
  self: DB =>

  def createUsername(username: String): Unit = {
    connect()

    try {
      System.out.println(s"Creating a user: $username")
    } finally {
      close()
    }
  }

  def fetchUser(username: String): Unit = {
    connect()

    try {
      System.out.println(s"Getting a user: $username")
    } finally {
      close()
    }
  }

}

trait UserService {
  self: UserDB =>

  // does not compile
  //def bad(): Unit =
  //dropDatabase()

}
