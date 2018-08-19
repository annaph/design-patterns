package org.design.patterns.structural.proxy

import java.io.{BufferedReader, InputStreamReader}

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

trait FileReader {

  def readFileContents(): String

}

class FileReaderReal(filename: String) extends FileReader {

  private val contents: String = {
    val reader = new BufferedReader(
      new InputStreamReader(this.getClass.getResourceAsStream(filename)))

    Try {
      reader.lines().iterator.asScala mkString System.getProperty("line.separator")
    } match {
      case Success(lines) =>
        reader.close()
        lines
      case Failure(exc) =>
        reader.close()
        throw new RuntimeException(exc)
    }
  }

  System.out.println(s"Finish reading the actual file: $filename")

  override def readFileContents(): String =
    contents

}

class FileReaderProxy(filename: String) extends FileReader {

  private lazy val fileReader = new FileReaderReal(filename)

  override def readFileContents(): String =
    fileReader.readFileContents()

}

object ProxyExample extends App {

  val fileMap = Map(
    "file1" -> new FileReaderProxy("file1.txt"),
    "file2" -> new FileReaderProxy("file2.txt"),
    "file3" -> new FileReaderProxy("file3.txt"),
    "file4" -> new FileReaderReal("file1.txt"))

  System.out.println("Created the map. You should have seen file1.txt read because it wasn't used in a proxy.")

  System.out.println(s"Reading file1.txt from the proxy: ${fileMap("file1").readFileContents()}")
  System.out.println(s"Reading file3.txt from the proxy: ${fileMap("file3").readFileContents()}")
}
