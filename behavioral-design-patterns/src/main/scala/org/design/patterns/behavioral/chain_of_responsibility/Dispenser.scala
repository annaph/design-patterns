package org.design.patterns.behavioral.chain_of_responsibility

trait Dispenser {

  val amount: Int
  val next: Option[Dispenser]

  def dispense(money: Money): Unit = {
    if (money.amount >= amount) {
      val notes: Int = money.amount / amount
      val left: Int = money.amount % amount

      System.out.println(s"Dispensing $notes note/s of $amount.")
      if (left > 0) next foreach (_.dispense(Money(left)))
    } else {
      next foreach (_.dispense(money))
    }
  }

}

class Dispenser50(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 50
}

class Dispenser20(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 20
}

class Dispenser10(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 10
}

class Dispenser5(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 5
}

trait PartialFunctionDispenser {

  def dispense(dispenserAmount: Int): PartialFunction[Money, Money] = {
    case m@Money(amount) if amount >= dispenserAmount =>
      val notes: Int = amount / dispenserAmount
      val left: Int = amount % dispenserAmount

      System.out.println(s"Dispensing $notes note/s of $dispenserAmount.")
      Money(left)
    case m@Money(_) =>
      m
  }

}
