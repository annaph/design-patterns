package org.design.patterns.creational.singleton

object StringUtils {

  def countNumberOfSpaces(text: String): Int =
    text.split("\\s+").length - 1

}

object UtilsExample extends App {

  import StringUtils.countNumberOfSpaces

  val sentence = "Hello there! I am a utils example."
  System.out.println(s"The number of spaces in '$sentence' is: ${countNumberOfSpaces(sentence)}")

}
