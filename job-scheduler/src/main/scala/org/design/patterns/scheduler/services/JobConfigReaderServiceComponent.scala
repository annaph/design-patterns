package org.design.patterns.scheduler.services

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import org.design.patterns.scheduler.config.app.AppConfigComponent
import org.design.patterns.scheduler.config.job.{JobConfig, JobFrequencySerializer, JobTypeSerializer}
import org.design.patterns.scheduler.io.IOServiceComponent
import org.json4s.jackson.JsonMethods.parse
import org.json4s.{DefaultFormats, FileInput, Formats}

import scala.util.{Failure, Success, Try}

trait JobConfigReaderServiceComponent {
  this: AppConfigComponent
    with IOServiceComponent =>

  val jobConfigReaderService: JobConfigReaderService

  class JobConfigReaderService extends LazyLogging {

    implicit val formats: Formats = DefaultFormats + JobFrequencySerializer + JobTypeSerializer +
      JobConfig.jobConfigFieldSerializer

    def readJobConfigs(): List[JobConfig] =
      ioService.getAllFilesWithExtension(appConfigService.configPath, appConfigService.configExtension)
        .flatMap { path =>
          Try {
            parse(FileInput(new File(path))).extract[JobConfig]
          } match {
            case Success(config) =>
              Some(config)
            case Failure(e) =>
              logger error("Error reading config: {}", path, e)
              None
          }
        }

  }

}
