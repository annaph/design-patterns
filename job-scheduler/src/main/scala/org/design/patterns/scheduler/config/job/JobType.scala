package org.design.patterns.scheduler.config.job

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JNull, JString}

sealed trait JobType

case object Console extends JobType

case object Sql extends JobType

case object JobTypeSerializer extends CustomSerializer[JobType](_ => ( {
  case JString("Console") =>
    Console
  case JString("Sql") =>
    Sql
  case JNull =>
    null
}, {
  case jobType =>
    JString(jobType.getClass.getSimpleName replace("$", ""))
}
))
