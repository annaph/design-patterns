package org.design.patterns.functional.implicits

package object di {

  implicit val databaseService: DatabaseService = new DatabaseServiceImpl
  implicit val userService: UserService = new UserServiceImpl

}
