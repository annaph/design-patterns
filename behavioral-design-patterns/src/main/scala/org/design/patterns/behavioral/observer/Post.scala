package org.design.patterns.behavioral.observer

import com.typesafe.scalalogging.LazyLogging

case class Post(user: User, text: String) extends Observable[Post] {

  import scala.collection.mutable

  private val _comments = mutable.ListBuffer.empty[Comment]

  def addComment(comment: Comment): Unit = {
    comment +=: _comments
    notifyObservers()
  }

  def comments: List[Comment] =
    _comments.toList

}

case class Comment(user: User, text: String)

case class User(name: String) extends Observer[Post] {

  override def handleUpdate(subject: Post): Unit =
    System.out.println(s"Hey, I'm $name. The post got some new comments: ${subject.comments}")

}

object PostExample extends App with LazyLogging {

  val userAnna = User("Anna")
  val userNicole = User("Nicole")
  val userMejv = User("Mejv")

  logger info "Create a post"
  val post = Post(userAnna, "This is a post about the observer design pattern")

  logger info "Add a comment"
  post addComment Comment(userAnna, "I hope you like the post!")

  logger info "Nicole and Mejv subscribe to the comments."
  post addObserver userNicole
  post addObserver userMejv

  logger info "Add a comment"
  post addComment Comment(userAnna, "Why are you so quiet? Do you like it?")

  logger info "Add a comment"
  post addComment Comment(userNicole, "It's amazing! Thanks!")

}
