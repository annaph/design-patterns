package org.design.patterns.functional.duck

import java.util.StringTokenizer

import scala.util.{Failure, Success, Try}

class SentenceParserTokenize {

  def parse(sentence: String): Array[String] = {
    val tokenizer = new StringTokenizer(sentence)

    Iterator.continually {
      Try {
        tokenizer.nextToken()
      } match {
        case Success(token) =>
          Some(token)
        case Failure(_) =>
          None
      }
    }.takeWhile(_.nonEmpty).map(_.get).toArray
  }

}
