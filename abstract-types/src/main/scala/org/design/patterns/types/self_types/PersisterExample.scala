package org.design.patterns.types.self_types

trait Persister[T] {
  self: Database[T] with History with Mistery =>

  def persist(data: T): Unit = {
    System.out.println("Calling persist.")
    self save data
    self.add()
  }

}

trait Database[T] {

  def save(data: T): Unit

}

trait MemoryDatabase[T] extends Database[T] {

  import scala.collection.mutable

  private val db = mutable.MutableList.empty[T]

  override def save(data: T): Unit = {
    System.out.println("Saving to in memory database.")
    db.+=:(data)
  }

}

trait FileDatabase[T] extends Database[T] {

  override def save(data: T): Unit =
    System.out.println("Saving to file.")

}

trait History {

  def add(): Unit =
    System.out.println("Action added to history.")

}

trait Mistery {

  def add(): Unit =
    System.out.println("Mistery added!")

}

class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mistery {

  override def add(): Unit =
    super[Mistery].add()

}

class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mistery {

  override def add(): Unit =
    super[History].add()

}

object PersisterExample extends App {

  val memoryIntPersister = new MemoryPersister[Int]
  val fileStringPersister = new FilePersister[String]

  memoryIntPersister persist 100
  memoryIntPersister persist 123

  fileStringPersister persist "Something"
  fileStringPersister persist "Something else"

}
