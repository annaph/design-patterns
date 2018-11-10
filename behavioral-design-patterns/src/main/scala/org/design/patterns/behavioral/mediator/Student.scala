package org.design.patterns.behavioral.mediator

trait Notifiable {

  def notify(message: String): Unit

}

case class Student(name: String, age: Int) extends Notifiable {

  override def notify(message: String): Unit =
    System.out.println(s"Student $name was notified with message: '$message'.")

}

case class Group(name: String)

trait Mediator {

  def addStudentToGroup(student: Student, group: Group): Unit

  def removeStudentFromGroup(student: Student, group: Group): Unit

  def isStudentInGroup(student: Student, group: Group): Boolean

  def getStudentsInGroup(group: Group): List[Student]

  def getGroupsForStudent(student: Student): List[Group]

  def notifyStudentsInGroup(group: Group, message: String): Unit

}

class School extends Mediator {

  import scala.collection.mutable

  private val studentsToGroups = mutable.Map.empty[Student, mutable.Set[Group]]
  private val groupsToStudents = mutable.Map.empty[Group, mutable.Set[Student]]

  override def addStudentToGroup(student: Student, group: Group): Unit = {
    studentsToGroups.getOrElseUpdate(student, mutable.Set.empty[Group]) += group
    groupsToStudents.getOrElseUpdate(group, mutable.Set.empty[Student]) += student
  }

  override def removeStudentFromGroup(student: Student, group: Group): Unit = {
    studentsToGroups.get(student) match {
      case Some(groups) =>
        groups -= group
      case None =>
    }

    groupsToStudents.get(group) match {
      case Some(students) =>
        students -= student
      case None =>
    }
  }

  override def isStudentInGroup(student: Student, group: Group): Boolean =
    (studentsToGroups.getOrElse(student, mutable.Set.empty[Group]) contains group) &&
      (groupsToStudents.getOrElse(group, mutable.Set.empty[Student]) contains student)

  override def getStudentsInGroup(group: Group): List[Student] =
    groupsToStudents.getOrElse(group, mutable.Set.empty[Student]).toList

  override def getGroupsForStudent(student: Student): List[Group] =
    studentsToGroups.getOrElse(student, mutable.Set.empty[Group]).toList

  override def notifyStudentsInGroup(group: Group, message: String): Unit =
    groupsToStudents.getOrElse(group, mutable.Set.empty[Student]).foreach(_.notify(message))

}

object SchoolExample extends App {

  val school = new School

  val student1 = Student("Anna", 33)
  val student2 = Student("Nicole", 28)
  val student3 = Student("Mejv", 18)

  val group1 = Group("Scala design patterns")
  val group2 = Group("Databases")
  val group3 = Group("Cloud computing")

  school addStudentToGroup(student1, group1)
  school addStudentToGroup(student1, group2)
  school addStudentToGroup(student1, group3)

  school addStudentToGroup(student2, group1)
  school addStudentToGroup(student2, group3)

  school addStudentToGroup(student3, group1)
  school addStudentToGroup(student3, group2)

  school notifyStudentsInGroup(group1, "Design patterns in Scala are amazing!")

  System.out.println(s"$student3 is in groups: ${school getGroupsForStudent student3}")
  school removeStudentFromGroup(student3, group2)
  System.out.println(s"$student3 is in groups: ${school getGroupsForStudent student3}")

  System.out.println(s"Students in $group1 are ${school getStudentsInGroup group1}")

}
