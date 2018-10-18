package org.design.patterns.behavioral.interpreter

import java.util.StringTokenizer

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class RNPParser {

  def parse(expression: String): Expression = {
    val tokenizer = new StringTokenizer(expression)

    tokenizer.asScala.foldLeft(mutable.Stack[Expression]()) {
      case (result, token) =>
        val item = Expression(token.toString, result.pop(), result.pop())
        item.foreach(result.push)

        result
    }.pop()
  }

}

class RNPInterpreter {

  def interpret(expression: Expression): Int =
    expression.interpret()

}

object RNPExample extends App {

  val expr1 = "1 2 + 3 * 9 10 + -" // // (1 + 2) * 3 - (9 + 10) = -10
  val expr2 = "1 2 3 4 5 * * - +" // 1 + 2 - (3 * 4 * 5) = -57
  val expr3 = "12 -" // invalid

  val parser = new RNPParser
  val interpreter = new RNPInterpreter

  System.out.println(s"The result of '$expr1' is: ${interpreter interpret (parser parse expr1)}")
  System.out.println(s"The result of '$expr2' is: ${interpreter interpret (parser parse expr2)}")

  Try {
    interpreter interpret (parser parse expr3)
  } match {
    case Success(result) =>
      System.out.println(s"The result of '$expr3' is: $result")
    case Failure(_) =>
      System.out.println(s"'$expr3' is invalid.")
  }

}
