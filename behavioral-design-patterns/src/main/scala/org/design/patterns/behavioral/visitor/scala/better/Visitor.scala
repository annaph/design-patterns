package org.design.patterns.behavioral.visitor.scala.better

object Visitor {

  private val line = System.getProperty("line.separator")

  def htmlExporterVisitor(builder: StringBuilder): Element => Unit = {
    case Title(text) =>
      builder.append(s"<h1>$text<h1>").append(line)
    case Text(text) =>
      builder.append(s"<p>$text</p>").append(line)
    case Hyperlink(text, url) =>
      builder.append(s"""<a href="$url">$text</a>""").append(line)
  }

  def plainTextExporterVisitor(builder: StringBuilder): Element => Unit = {
    case Title(text) =>
      builder.append(text).append(line)
    case Text(text) =>
      builder.append(text).append(line)
    case Hyperlink(url, text) =>
      builder.append(s"$text ($url)").append(line)
  }

}

object VisitorExample extends App {

  import Visitor.{htmlExporterVisitor, plainTextExporterVisitor}

  val document = new Document(
    List(
      Title("The Visitor Pattern Example"),
      Text("The visitor pattern helps us add extra functionality without changing the classes."),
      Hyperlink("Go check it online!", "https://www.google.com/"),
      Text("Thanks!")))

  val html = new StringBuilder
  System.out.println("Export to html:")
  document accept htmlExporterVisitor(html)
  System.out.println(html.toString)

  val plain = new StringBuilder
  System.out.println("Export to plain:")
  document accept plainTextExporterVisitor(plain)
  System.out.println(plain.toString)

}
