package org.design.patterns.scheduler.config.job

import org.json4s.FieldSerializer
import org.json4s.JsonAST.JField

case class JobConfig(name: String,
                     command: String,
                     frequency: JobFrequency,
                     jobType: JobType,
                     timeOptions: TimeOptions)

object JobConfig {

  val jobConfigFieldSerializer: FieldSerializer[JobConfig] = FieldSerializer[JobConfig](
    {
      case ("jobType", x) => Some("type", x)
      case ("timeOptions", x) => Some("time_options", x)
    },
    {
      case JField("type", x) => JField("jobType", x)
      case JField("time_options", x) => JField("timeOptions", x)
    }
  )

}
