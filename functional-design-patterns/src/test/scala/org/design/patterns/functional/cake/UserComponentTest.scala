package org.design.patterns.functional.cake

import org.design.patterns.functional.cake.model.Person
import org.mockito.Mockito.{verify, when}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

class UserComponentTest
  extends FlatSpec
    with Matchers
    with MockitoSugar
    with TestEnvironment {

  override val userService = new UserService

  val className = "A"
  val emptyClassName = "B"
  val people = List(
    Person(1, "a", 10),
    Person(2, "b", 15),
    Person(3, "c", 20)
  )

  when(daoService.getPeopleInClass(className))
    .thenReturn(people)

  when(daoService.getPeopleInClass(emptyClassName))
    .thenReturn(List.empty[Person])

  "getAverageAgeOfUsersInClass" should "properly calculate the average age of all ages." in {
    userService.getAverageAgeOfUsersInClass(className) should equal(15.0)
    verify(daoService).getPeopleInClass(className)
  }

  it should "properly handle an empty result." in {
    userService.getAverageAgeOfUsersInClass(emptyClassName) should equal(0.0)
    verify(daoService).getPeopleInClass(emptyClassName)
  }

}
