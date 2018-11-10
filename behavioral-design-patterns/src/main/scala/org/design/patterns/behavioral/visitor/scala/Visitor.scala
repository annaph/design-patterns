package org.design.patterns.behavioral.visitor.scala

trait Visitor {

  def visit(element: Element): Unit

}

class HtmlExporterVisitor extends Visitor {

  private val line = System.getProperty("line.separator")

  private val builder = new StringBuilder

  def getHtml: String =
    builder.toString

  override def visit(element: Element): Unit = element match {
    case Title(text) =>
      builder.append(s"<h1>$text<h1>").append(line)
    case Text(text) =>
      builder.append(s"<p>$text</p>").append(line)
    case Hyperlink(text, url) =>
      builder.append(s"""<a href="$url">$text</a>""").append(line)
  }

}

class PlainTextExporterVisitor extends Visitor {

  private val line = System.getProperty("line.separator")

  private val builder = new StringBuilder

  def getText: String =
    builder.toString

  override def visit(element: Element): Unit = element match {
    case Title(text) =>
      builder.append(text).append(line)
    case Text(text) =>
      builder.append(text).append(line)
    case Hyperlink(url, text) =>
      builder.append(s"$text ($url)").append(line)
  }

}

object VisitorExample extends App {

  val document = new Document(
    List(
      Title("The Visitor Pattern Example"),
      Text("The visitor pattern helps us add extra functionality without changing the classes."),
      Hyperlink("Go check it online!", "https://www.google.com/"),
      Text("Thanks!")))

  val htmlExporter = new HtmlExporterVisitor
  val plainTextExporter = new PlainTextExporterVisitor

  System.out.println("Export to html:")
  document accept htmlExporter
  System.out.println(htmlExporter.getHtml)

  System.out.println("Export to plain:")
  document accept plainTextExporter
  System.out.println(plainTextExporter.getText)

}
