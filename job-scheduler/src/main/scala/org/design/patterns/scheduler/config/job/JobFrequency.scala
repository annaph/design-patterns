package org.design.patterns.scheduler.config.job

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JNull, JString}

sealed trait JobFrequency

case object Daily extends JobFrequency

case object Hourly extends JobFrequency

case object JobFrequencySerializer extends CustomSerializer[JobFrequency](_ => ( {
  case JString("Daily") =>
    Daily
  case JString("Hourly") =>
    Hourly
  case JNull =>
    null
}, {
  case jobFrequency =>
    JString(jobFrequency.getClass.getSimpleName replace("$", ""))
}
))
