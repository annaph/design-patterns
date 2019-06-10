package org.design.patterns.applications.monads

import org.design.patterns.applications.monads.model.Person
import scalaz.Scalaz._
import scalaz.effect.IO
import scalaz.effect.IO._

object IOMonadExample extends App {

  args match {
    case Array(inputFile, isWriteToFile) =>
      val people = readInputFile(inputFile)

      System.out.println("Still haven't done any IO!")
      System.out.println("About to so some...")

      if (isWriteToFile.toBoolean) {
        val writePeople = writeOutputFile(people)

        System.out.println("Writing to file using toString.")
        writePeople.unsafePerformIO()
      } else {
        System.out.println(s"Just got the following people: ${people.unsafePerformIO().toList}")
      }
    case _ =>
      System.err.println("Please provide some input file and true/false whether to write to file.")
      System exit -1
  }

  def readInputFile(inputFile: String): IO[Iterator[Person]] =
    for {
      lines <- readFile(inputFile).pure[IO]
      parsedLines <- lines.map(line => Person fromArray (line split "\t")).pure[IO]
      people <- parsedLines.filter(_.nonEmpty).map(_.get).pure[IO]
    } yield people

  def writeOutputFile(people: IO[Iterator[Person]]): IO[Unit] =
    for {
      _ <- putStrLn("Read people successfully. Where to write them down?")
      outputFile <- readLn
      p <- people
      _ <- writeFile(outputFile, p.map(_.toString)).pure[IO]
    } yield ()

}
