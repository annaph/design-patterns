package org.design.patterns.functional.duck

class SentenceParserSplit {

  def parse(sentence: String): Array[String] =
    sentence split "\\s"

}
