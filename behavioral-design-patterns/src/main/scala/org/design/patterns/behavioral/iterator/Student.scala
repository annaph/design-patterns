package org.design.patterns.behavioral.iterator

case class Student(name: String, age: Int)

class StudentIterator(students: Array[Student]) extends Iterator[Student] {
  private var currentPos = 0

  override def hasNext: Boolean =
    currentPos < students.length

  override def next(): Student = {
    val result = students(currentPos)
    currentPos = currentPos + 1

    result
  }

}

class ClassRoom extends Iterable[Student] {

  import scala.collection.mutable

  private val students = mutable.ListBuffer.empty[Student]

  def add(student: Student): Unit =
    student +=: students

  override def iterator: Iterator[Student] =
    new StudentIterator(students.toArray)

}

object ClassRoomExample extends App {

  val classRoom = new ClassRoom

  classRoom add Student("Anna", 33)
  classRoom add Student("Nicole", 24)
  classRoom add Student("Mejv", 18)

  classRoom foreach println

}
