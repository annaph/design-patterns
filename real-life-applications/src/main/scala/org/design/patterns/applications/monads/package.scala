package org.design.patterns.applications

import java.io.{File, PrintWriter}

import scala.io.Source
import scala.util.{Failure, Success, Try}

package object monads {

  def readFile(path: String): Iterator[String] = {
    System.out.println(s"Reading file $path")
    Source.fromFile(path).getLines()
  }

  def writeFile(path: String, lines: Iterator[String]): Unit = {
    System.out.println(s"Writing file $path")
    printToFile(new File(path))(lines foreach _.println)
  }

  private def printToFile(file: File)(writerOp: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    Try {
      writerOp(writer)
    } match {
      case Success(_) =>
        writer.close()
      case Failure(e) =>
        writer.close()
        throw new Exception(s"Error writing file ${file.getCanonicalPath}", e)
    }
  }

}
