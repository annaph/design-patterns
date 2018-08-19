package org.design.patterns.structural.decorator

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.structural.decorator.common.{AdvancedInputReader, InputReader}

trait CapitalizedInputReaderTrait extends InputReader {

  abstract override def readLines(): Stream[String] =
    super.readLines().map(_.toUpperCase())

}

trait CompressingInputReaderTrait extends InputReader with LazyLogging {

  abstract override def readLines(): Stream[String] =
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

trait Base64EncoderInputReaderTrait extends InputReader {

  abstract override def readLines(): Stream[String] =
    super.readLines().map { line =>
      Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
    }

}

object StackableTraitsExample extends App {

  val stream = new BufferedReader(
    new InputStreamReader(
      new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))))

  try {
    val reader = new AdvancedInputReader(stream) with CapitalizedInputReaderTrait
    reader.readLines().foreach(println)
  } finally {
    stream.close()
  }

}

object StackableTraitsBigExample extends App {

  val stream = new BufferedReader(
    new InputStreamReader(
      new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))))

  try {
    val reader = new AdvancedInputReader(stream)
      with CapitalizedInputReaderTrait
      with Base64EncoderInputReaderTrait
      with CompressingInputReaderTrait
    reader.readLines().foreach(println)
  } finally {
    stream.close()
  }

}
