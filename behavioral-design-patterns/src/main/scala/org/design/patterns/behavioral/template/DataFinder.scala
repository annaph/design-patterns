package org.design.patterns.behavioral.template

import java.io.{ByteArrayInputStream, InputStreamReader}

import com.github.tototoshi.csv.CSVReader
import org.design.patterns.behavioral.template.model.Person
import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, Formats, StringInput}

import scala.util.{Failure, Success, Try}

abstract class DataFinder[T, Y] {

  def find(f: T => Option[Y]): Option[Y] = {
    Try {
      val data = readData()
      val parsed = parseData(data)
      f(parsed)
    } match {
      case Success(result) =>
        cleanup()
        result
      case Failure(e) =>
        cleanup()
        throw new RuntimeException("Error finding result!", e)
    }
  }

  def readData(): Array[Byte]

  def parseData(data: Array[Byte]): T

  def cleanup(): Unit

}

class JsonDataFinder extends DataFinder[List[Person], Person] {

  implicit private val formats: Formats = DefaultFormats

  override def readData(): Array[Byte] = {
    val stream = this.getClass.getResourceAsStream("people.json")
    Stream.continually(stream.read).takeWhile(_ != -1).map(_.toByte).toArray
  }

  override def parseData(data: Array[Byte]): List[Person] =
    JsonMethods.parse(StringInput(new String(data, "UTF-8")))
      .extract[List[Person]]

  def cleanup(): Unit =
    System.out.println("Reading json: nothing to do.")

}

class CSVDataFinder extends DataFinder[List[Person], Person] {

  override def readData(): Array[Byte] = {
    val stream = this.getClass.getResourceAsStream("people.csv")
    Stream.continually(stream.read).takeWhile(_ != -1).map(_.toByte).toArray
  }

  override def parseData(data: Array[Byte]): List[Person] =
    CSVReader.open(new InputStreamReader(new ByteArrayInputStream(data))).all().map {
      case List(name, age, address) =>
        Person(name, age.toInt, address)
    }

  override def cleanup(): Unit =
    System.out.println("Reading csv: nothing to do.")

}

object DataFinderExample extends App {

  val jsonDataFinder: DataFinder[List[Person], Person] = new JsonDataFinder
  val csvDataFinder: DataFinder[List[Person], Person] = new CSVDataFinder

  System.out.println(s"Find a person with name Ivan in the json: ${jsonDataFinder find (_.find(_.name == "Ivan"))}")
  System.out.println(s"Find a person with name James in the json: ${jsonDataFinder find (_.find(_.name == "James"))}")

  System.out.println(s"Find a person with name Maria in the csv: ${csvDataFinder find (_.find(_.name == "Maria"))}")
  System.out.println(s"Find a person with name Alice in the csv: ${csvDataFinder find (_.find(_.name == "Alice"))}")

}
