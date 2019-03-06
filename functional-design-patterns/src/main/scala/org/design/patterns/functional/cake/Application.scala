package org.design.patterns.functional.cake

import org.design.patterns.functional.cake.ApplicationComponentRegistry._

object Application extends App {

  migrationService.runMigrations()

  System.out.println("\nAll people:\n\t\t\t" + daoService.getPeople + "\n")
  System.out.println("\nAll classes:\n\t\t\t" + daoService.getClasses + "\n")

  System.out.println(s"\nAll people in 'Scala Design Patterns' group:\n\t\t\t${
    daoService getPeopleInClass "Scala Design Patterns"
  }\n")

  System.out.println(s"\nAll people in 'Mountain Biking' group:\n\t\t\t${
    daoService getPeopleInClass "Mountain Biking"
  }\n")

  System.out.println(s"\nAverage age of everyone in 'Scala Design Patterns' group:\t${
    userService getAverageAgeOfUsersInClass "Scala Design Patterns"
  }")

}
