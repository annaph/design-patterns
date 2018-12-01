package org.design.patterns.deep.theory.monads.io

sealed trait State {
  def next: State
}

abstract class FileIO {

  def run(args: Array[String]): Unit = {
    val action = runIO(args(0), args(1))
    action(new FileIOState(0))
  }

  def runIO(readPath: String, writePath: String): IOAction[_]

  private class FileIOState(id: Int) extends State {
    override def next: State = new FileIOState(id + 1)
  }

}

sealed abstract class IOAction[T] extends (State => (State, T)) {

  def map[Y](f: T => Y): IOAction[Y] =
    flatMap(t => unit(f(t)))

  def unit[Y](value: Y): IOAction[Y] =
    IOAction.unit(value)

  def flatMap[Y](f: T => IOAction[Y]): IOAction[Y] = {
    val self = this
    new IOAction[Y] {
      override def apply(state: State): (State, Y) = {
        val (state1, res1) = self(state)
        val action2 = f(res1)
        action2(state1)
      }
    }
  }

}

object IOAction {

  def apply[T](result: => T): IOAction[T] =
    new SimpleAction(result)

  def unit[T](value: T): IOAction[T] =
    new EmptyAction(value)

  private class SimpleAction[T](result: => T) extends IOAction[T] {
    override def apply(state: State): (State, T) =
      state.next -> result
  }

  private class EmptyAction[T](value: T) extends IOAction[T] {
    override def apply(state: State): (State, T) =
      state -> value
  }

}

object FileIOExample extends FileIO with App {

  run(args)

  override def runIO(readPath: String, writePath: String): IOAction[Unit] =
    for {
      lines <- readFile(readPath)
      _ <- writeFile(writePath, lines.map(_.toUpperCase))
    } yield ()

}
