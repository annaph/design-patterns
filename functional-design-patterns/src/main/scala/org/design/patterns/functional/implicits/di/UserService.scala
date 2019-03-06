package org.design.patterns.functional.implicits.di

trait UserService {

  def averageAgeOfPeople(implicit ds: DatabaseService): Double

}

class UserServiceImpl extends UserService {

  override def averageAgeOfPeople(implicit ds: DatabaseService): Double = {
    val (sum, count) = ds.people.foldLeft(0 -> 0) {
      case ((s, c), person) =>
        (s + person.age) -> (c + 1)
    }

    sum.toDouble / count.toDouble
  }

}
