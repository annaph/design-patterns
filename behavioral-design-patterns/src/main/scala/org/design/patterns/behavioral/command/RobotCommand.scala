package org.design.patterns.behavioral.command

trait RobotCommand {

  def execute(): Unit

}

case class MakeSandwichCommand(robot: Robot) extends RobotCommand {

  override def execute(): Unit =
    robot.makeSandwich()

}

case class PourJuiceCommand(robot: Robot) extends RobotCommand {

  override def execute(): Unit =
    robot.pourJuice()

}

case class CleanUpCommand(robot: Robot) extends RobotCommand {

  override def execute(): Unit =
    robot.cleanUp()

}

class RobotController {

  import scala.collection.mutable.ListBuffer

  private val history = ListBuffer.empty[RobotCommand]

  def issueCommand(command: RobotCommand): Unit = {
    command +=: history
    command.execute()
  }

  def showHistory(): Unit =
    history foreach println

}

class RobotByNameController {

  import scala.collection.mutable.ListBuffer

  private val history = ListBuffer.empty[() => Unit]

  def issueCommand(command: => Unit): Unit = {
    command _ +=: history
    command
  }

  def showHistory(): Unit =
    history foreach println

}

object RobotExample extends App {

  val robot = Robot()
  val robotController = new RobotController

  robotController issueCommand MakeSandwichCommand(robot)
  robotController issueCommand PourJuiceCommand(robot)
  System.out.println("I'm eating and having some juice.")

  robotController issueCommand CleanUpCommand(robot)

  System.out.println("Here is what I asked my robot to do:")
  robotController.showHistory()

}

object RobotByNameExample extends App {

  val robot = Robot()
  val robotController = new RobotByNameController

  robotController issueCommand MakeSandwichCommand(robot).execute()
  robotController issueCommand PourJuiceCommand(robot).execute()
  System.out.println("I'm eating and having some juice.")

  robotController issueCommand CleanUpCommand(robot).execute()

  System.out.println("Here is what I asked my robot to do:")
  robotController.showHistory()

}
