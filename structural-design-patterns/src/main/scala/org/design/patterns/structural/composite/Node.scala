package org.design.patterns.structural.composite

import scala.collection.mutable.ListBuffer

trait Node {

  def print(prefix: String): Unit

}

class Leaf(data: String) extends Node {

  override def print(prefix: String): Unit =
    System.out.println(s"$prefix$data")

}

class Tree extends Node {

  private val children = ListBuffer.empty[Node]

  override def print(prefix: String): Unit = {
    System.out.println(s"$prefix(")
    children.foreach(_.print(s"$prefix$prefix"))
    System.out.println(s"$prefix)")
  }

  def add(child: Node): Unit =
    children += child

  def remove(): Unit =
    if (children.nonEmpty) children remove 0

}

object CompositeExample extends App {

  val tree = new Tree

  tree add new Leaf("leaf 1")

  val subtree1 = new Tree
  tree add subtree1

  subtree1 add new Leaf("leaf 2")

  val subtree2 = new Tree
  subtree1 add subtree2

  subtree2 add new Leaf("leaf 3")
  subtree2 add new Leaf("leaf 4")

  val subtree3 = new Tree
  tree add subtree3

  val subtree4 = new Tree
  subtree3 add subtree4

  subtree4 add new Leaf("leaf 5")
  subtree4 add new Leaf("leaf 6")

  tree print "-"
}
