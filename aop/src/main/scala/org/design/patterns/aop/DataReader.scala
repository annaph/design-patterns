package org.design.patterns.aop

import org.design.patterns.aop.model.Person
import org.json4s.jackson.JsonMethods.parse
import org.json4s.{DefaultFormats, StreamInput}

trait DataReader {

  def readData(): List[Person]

  def readDataInefficiently(): List[Person]

}

class DataReaderImpl extends DataReader {

  implicit val formats = DefaultFormats

  override def readData(): List[Person] =
    readUntimed()

  override def readDataInefficiently(): List[Person] = {
    (1 to 10000).foreach {
      case _ =>
        readUntimed()
    }

    readUntimed()
  }

  private def readUntimed(): List[Person] =
    parse(StreamInput(getClass.getResourceAsStream("/users.json"))).extract[List[Person]]

  // Uncomment for using the timing without AOP.
  /**
    * override def readData(): List[Person] = {
    * val startMillis = System.currentTimeMillis()
    * val result = readUntimed()
    * val time = System.currentTimeMillis() - startMillis
    *System.err.println(s"readData took ${time} milliseconds.")
    * *
    * result
    * }
    * *
    * override def readDataInefficiently(): List[Person] = {
    * val startMillis = System.currentTimeMillis()
    * (1 to 10000).foreach {
    * case _ =>
    * readUntimed()
    * }
    * val result = readUntimed()
    * val time = System.currentTimeMillis() - startMillis
    *System.err.println(s"readDataInefficiently tokk ${time} milliseconds.")
    * *
    * result
    * }
    **/

}

object DataReaderExample extends App {

  val dataReader = new DataReaderImpl

  System.out.println(s"I just read the following data efficiently: ${dataReader.readData()}")
  System.out.println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")

}

object DataReaderAOPExample extends App {

  val dataReader = new DataReaderImpl with LoggingDataReader

  System.out.println(s"I just read the following data efficiently: ${dataReader.readData()}")
  System.out.println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")

}
