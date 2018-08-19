package org.design.patterns.structural

package object adapter {

  implicit class FinalAppLoggerImplicit(logger: FinalLogger) extends Log {

    override def info(message: String): Unit =
      logger log(message, "info")

    override def debug(message: String): Unit =
      logger log(message, "debug")

    override def warning(message: String): Unit =
      logger log(message, "warning")

    override def error(message: String): Unit =
      logger log(message, "error")

  }

}
