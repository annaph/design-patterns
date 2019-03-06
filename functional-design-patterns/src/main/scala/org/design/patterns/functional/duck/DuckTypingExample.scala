package org.design.patterns.functional.duck

object DuckTypingExample extends App {

  val tokenizerParser = new SentenceParserTokenize
  val splitParser = new SentenceParserSplit

  val sentence = "This is the sentence we will be splitting."

  System.out.println("Using the tokenizer parser: ")
  printSentenceParts(sentence, tokenizerParser)

  System.out.println("Using the split parsers: ")
  printSentenceParts(sentence, splitParser)

  def printSentenceParts(sentence: String, parser: {def parse(sentence: String): Array[String]}): Unit =
    parser.parse(sentence).foreach(println)

}
