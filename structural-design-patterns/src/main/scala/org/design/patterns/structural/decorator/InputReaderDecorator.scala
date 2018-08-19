package org.design.patterns.structural.decorator

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.structural.decorator.common.{AdvancedInputReader, InputReader}

abstract class InputReaderDecorator(inputReader: InputReader) extends InputReader {

  override def readLines(): Stream[String] =
    inputReader.readLines()

}

class CapitalizedInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {

  override def readLines(): Stream[String] =
    super.readLines().map(_.toUpperCase())

}

class CompressingInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) with LazyLogging {

  override def readLines(): Stream[String] =
    super.readLines().map { line =>
      val text: Array[Byte] = line.getBytes(Charset.forName("UTF-8"))
      logger info("Length before compressing: {}", text.length)

      val output = new ByteArrayOutputStream()
      val compressor = new GZIPOutputStream(output)

      try {
        compressor write(text, 0, text.length)

        val outputByteArray: Array[Byte] = output.toByteArray
        logger info("Length after compressing: {}", outputByteArray.length)

        new String(outputByteArray, Charset.forName("UTF-8"))
      } finally {
        compressor.close()
        output.close()
      }
    }

}

class Base64EncoderInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {

  override def readLines(): Stream[String] =
    super.readLines().map { line =>
      Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
    }

}

object DecoratorExample extends App {

  val stream = new BufferedReader(
    new InputStreamReader(
      new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))))

  try {
    val reader = new CapitalizedInputReader(new AdvancedInputReader(stream))
    reader.readLines().foreach(println)
  } finally {
    stream.close()
  }

}

object DecoratorExampleBig extends App {

  val stream = new BufferedReader(
    new InputStreamReader(
      new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))))

  try {
    val reader = new CompressingInputReader(
      new Base64EncoderInputReader(
        new CapitalizedInputReader(
          new AdvancedInputReader(stream))))
    reader.readLines().foreach(println)
  } finally {
    stream.close()
  }
}
