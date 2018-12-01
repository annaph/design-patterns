package org.design.patterns.deep.theory.monads

import java.io.{File, PrintWriter}

import scala.io.Source
import scala.util.Try

package object io {

  def readFile(path: String): IOAction[Iterator[String]] =
    IOAction {
      Source.fromFile(path).getLines
    }

  def writeFile(path: String, lines: Iterator[String]): IOAction[Unit] =
    IOAction {
      printToFile(new File(path)) { writer =>
        lines.foreach(writer.println)
      }
    }

  def printToFile(file: File)(writerOp: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    Try {
      writerOp(writer)
    } match {
      case _ => writer.close()
    }

  }

}
