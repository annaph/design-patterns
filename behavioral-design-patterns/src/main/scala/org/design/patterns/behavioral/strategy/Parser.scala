package org.design.patterns.behavioral.strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.design.patterns.behavioral.strategy.model.Person
import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, Formats, StreamInput}

trait Parser[T] {

  def parse(file: String): List[T]

}

class CSVParser extends Parser[Person] {

  override def parse(file: String): List[Person] = {
    val reader = CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file)))

    reader.toStream.map {
      case name :: age :: address :: Nil =>
        Person(name, age.toInt, address)
      case _ =>
        throw new RuntimeException("Invalid input file!")
    }.toList
  }

}

class JsonParser extends Parser[Person] {
  implicit val formats: Formats = DefaultFormats

  override def parse(file: String): List[Person] =
    JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file)))
      .extract[List[Person]]

}

object Parser {

  def apply(filename: String): Parser[Person] =
    filename match {
      case f if f endsWith ".json" =>
        new JsonParser
      case f if f endsWith ".csv" =>
        new CSVParser
      case f =>
        throw new RuntimeException(s"Unknown format: $f")
    }

}

class PersonApplication[T](parser: Parser[T]) {

  def write(file: String): Unit =
    System.out.println(s"Got the following data ${parser parse file}")

}

object ParserExample extends App {

  val csvPeople = Parser("people.csv")
  val jsonPeople = Parser("people.json")

  val applicationCsv = new PersonApplication(csvPeople)
  val applicationJson = new PersonApplication(jsonPeople)

  System.out.println("Using the csv: ")
  applicationCsv write "people.csv"

  System.out.println("Using the json: ")
  applicationJson write "people.json"

}
