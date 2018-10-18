package org.design.patterns.behavioral.chain_of_responsibility

import scala.io.Source
import scala.util.{Failure, Success, Try}

class ATM {

  private val dispenser = {
    val dispenser5 = new Dispenser5(None)
    val dispenser10 = new Dispenser10(Some(dispenser5))
    val dispenser20 = new Dispenser20(Some(dispenser10))

    new Dispenser50(Some(dispenser20))
  }

  def requestMoney(money: Money): Unit = {
    if (money.amount % 5 == 0) {
      dispenser dispense money
    } else {
      System.err.println("The smallest nominal is 5 and we cannot satisfy your request.")
    }
  }

}

class PartialFunctionATM extends PartialFunctionDispenser {

  private val dispenser = dispense(50)
    .andThen(dispense(20))
    .andThen(dispense(10))
    .andThen(dispense(5))

  def requestMoney(money: Money): Unit = {
    if (money.amount % 5 == 0) {
      dispenser(money)
    } else {
      System.err.println("The smallest nominal is 5 and we cannot satisfy your request.")
    }
  }

}

object ATMExample extends App {

  val atm = new ATM

  printHelp()
  Source.stdin.getLines().foreach {
    processLine(_, atm)
  }

  private def processLine(line: String, atm: ATM): Unit =
    line match {
      case "EXIT" =>
        System.out.println("Bye!")
        System exit 0
      case _ =>
        Try {
          atm requestMoney Money(line.toInt)
        } match {
          case Success(_) =>
            System.out.println("Thanks!")
          case Failure(_) =>
            System.err.println(s"Invalid input: $line.")
            printHelp()
        }
    }

  private def printHelp(): Unit = {
    System.out.println("Usage: ")
    System.out.println("1. Write an amount to withdraw...")
    System.out.println("2. Write EXIT to quit application.")
  }

}

object PartialFunctionATMExample extends App {

  val atm = new PartialFunctionATM

  printHelp()
  Source.stdin.getLines().foreach {
    processLine(_, atm)
  }

  private def processLine(line: String, atm: PartialFunctionATM): Unit =
    line match {
      case "EXIT" =>
        System.out.println("Bye!")
        System exit 0
      case _ =>
        Try {
          atm requestMoney Money(line.toInt)
        } match {
          case Success(_) =>
            System.out.println("Thanks!")
          case Failure(_) =>
            System.err.println(s"Invalid input: $line.")
            printHelp()
        }
    }

  private def printHelp(): Unit = {
    System.out.println("Usage: ")
    System.out.println("1. Write an amount to withdraw...")
    System.out.println("2. Write EXIT to quit application.")
  }

}
