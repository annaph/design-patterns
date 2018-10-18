package org.design.patterns.behavioral.interpreter

trait Expression {

  def interpret(): Int

}

class Number(n: Int) extends Expression {

  override def interpret(): Int = n

}

class Add(right: Expression, left: Expression) extends Expression {

  override def interpret(): Int =
    left.interpret() + right.interpret()

}

class Subtract(right: Expression, left: Expression) extends Expression {

  override def interpret(): Int =
    left.interpret() - right.interpret()

}

class Multiply(right: Expression, left: Expression) extends Expression {

  override def interpret(): Int =
    left.interpret() * right.interpret()

}

object Expression {

  def apply(operator: String, left: => Expression, right: => Expression): Option[Expression] = {
    operator match {
      case "+" =>
        Some(new Add(right, left))
      case "-" =>
        Some(new Subtract(right, left))
      case "*" =>
        Some(new Multiply(right, left))
      case i if i matches "\\d+" =>
        Some(new Number(i.toInt))
      case _ =>
        None
    }
  }

}
