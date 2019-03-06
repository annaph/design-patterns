package org.design.patterns.functional.pimp

import org.design.patterns.functional.pimp.model.Person

object PimpExample extends App {

  System.out.println(s"Is 'test' all upper case: ${"test".isAllUpperCase}")
  System.out.println(s"Is 'Tes' all upper case: ${"Tes".isAllUpperCase}")
  System.out.println(s"Is 'TESt' all upper case: ${"TESTt".isAllUpperCase}")
  System.out.println(s"Is 'TEST' all upper case: ${"TEST".isAllUpperCase}")

}

object PimpExample2 extends App {

  val people = List(
    Person("Anna", 33),
    Person("Maria", 19),
    Person("John", 29)
  )

  people.saveToDatabase()

}
