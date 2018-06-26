package org.design.patterns.components

import org.design.patterns.components.model.Food

class Robot extends RobotRegistry {

  def cook(what: String): Food =
    cooker cook what

  def getTime: String =
    time.getTime

}

object RobotExample extends App {

  val robot = new Robot

  System.out.println(robot.getTime);

  System.out.println(robot cook "chips")
  System.out.println(robot cook "sandwich")

}
