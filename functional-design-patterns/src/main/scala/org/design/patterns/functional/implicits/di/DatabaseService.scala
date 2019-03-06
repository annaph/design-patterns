package org.design.patterns.functional.implicits.di

import org.design.patterns.functional.implicits.di.model.Person

trait DatabaseService {

  def people: List[Person]

}

class DatabaseServiceImpl extends DatabaseService {

  override def people: List[Person] =
    List(
      Person("Anna", 34),
      Person("Maria", 26),
      Person("Nicole", 19))

}
