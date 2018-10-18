package org.design.patterns.behavioral.strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.design.patterns.behavioral.strategy.model.Person
import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, Formats, StreamInput}

class Application[T](strategy: String => List[T]) {

  def write(file: String): Unit =
    System.out.println(s"Got the following data: ${strategy(file)}")

}

object StrategyFactory {

  implicit private val formats: Formats = DefaultFormats

  def apply(filename: String): String => List[Person] =
    filename match {
      case f if f endsWith ".csv" =>
        parseCsv
      case f if f endsWith ".json" =>
        parseJson
      case f =>
        throw new RuntimeException(s"Unknown format: $f")
    }

  private def parseCsv(file: String): List[Person] = {
    val reader = CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file)))

    reader.toStream.map {
      case name :: age :: address :: Nil =>
        Person(name, age.toInt, address)
      case _ =>
        throw new RuntimeException("Invalid input file!")
    }.toList
  }

  private def parseJson(file: String): List[Person] =
    JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file)))
      .extract[List[Person]]

}

object StrategyExample extends App {

  val applicationCsv = new Application[Person](StrategyFactory("people.csv"))
  val applicationJson = new Application[Person](StrategyFactory("people.json"))

  System.out.println("Using the csv: ")
  applicationCsv write "people.csv"

  System.out.println(s"Using the json: ")
  applicationJson write "people.json"

}

