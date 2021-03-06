package org.design.patterns.behavioral.memento

class TextEditor extends Originator[String] {

  private var builder = new StringBuilder

  def append(text: String): Unit =
    builder append text

  def delete(): Unit =
    if (builder.nonEmpty) builder deleteCharAt (builder.length - 1)

  def text: String =
    builder.toString

  override def createMemento: Memento[String] =
    new TextEditorMemento(builder.toString)

  override def restore(memento: Memento[String]): Unit =
    builder = new StringBuilder(memento.getState)

  private class TextEditorMemento(val state: String) extends Memento[String]

}

class TextEditorManipulator extends Caretaker[String] {

  val textEditor = new TextEditor

  def append(text: String): Unit = {
    save()
    textEditor append text
  }

  def delete(): Unit = {
    save()
    textEditor.delete()
  }

  def readText: String =
    textEditor.text

  def undo(): Unit =
    states.headOption.foreach(memento => {
      states trimStart 1
      textEditor restore memento
    })

  private def save(): Unit =
    textEditor.createMemento +=: states

}

object TextEditorExample extends App {

  val textEditorManipulator = new TextEditorManipulator

  textEditorManipulator append "This is a chapter about memento."
  System.out.println(s"The text is: '${textEditorManipulator.readText}'")

  System.out.println("Deleting 2 characters...")
  textEditorManipulator.delete()
  textEditorManipulator.delete()
  System.out.println(s"The text is: '${textEditorManipulator.readText}'")

  System.out.println("Undoing...")
  textEditorManipulator.undo()
  System.out.println(s"The text is: '${textEditorManipulator.readText}'")

  System.out.println("Undoing...")
  textEditorManipulator.undo()
  System.out.println(s"The text is: '${textEditorManipulator.readText}'")

}
