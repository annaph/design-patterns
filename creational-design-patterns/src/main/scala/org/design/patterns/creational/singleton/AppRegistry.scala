package org.design.patterns.creational.singleton

import scala.collection.concurrent.{Map, TrieMap}

object AppRegistry {

  System.out.println(s"Registry initialization block called.")

  private val users: Map[String, String] = TrieMap.empty

  def addUser(id: String, name: String): Unit =
    users put(id, name)

  def removeUser(id: String): Unit =
    users remove id

  def isUserRegistered(id: String): Boolean =
    users contains id

  def allUsers: List[String] =
    users.values.toList

}

object AppRegistryExample extends App {

  import AppRegistry._

  System.out.println("Sleeping for 5 seconds.")
  Thread sleep 5000
  System.out.println("I woke up.")

  addUser("1", "John")
  addUser("2", "Ivan")
  addUser("3", "Martin")

  System.out.println(s"Is user with ID=1 registered? ${isUserRegistered("1")}")

  System.out.println("Removing ID=2")
  removeUser("2")

  System.out.println(s"Is user with ID=2 registered? ${isUserRegistered("2")}")

  System.out.println(s"All users registered are: ${allUsers mkString ","}")

}
