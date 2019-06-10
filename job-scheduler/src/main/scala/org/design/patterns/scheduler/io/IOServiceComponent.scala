package org.design.patterns.scheduler.io

import java.io.File

trait IOServiceComponent {

  val ioService: IOService

  class IOService {

    def getAllFilesWithExtension(basePath: String, extension: String): List[String] = {
      new File(basePath) match {
        case dir if dir.exists && dir.isDirectory =>
          dir.listFiles()
            .filter(_.isFile)
            .filter(_.getPath.toLowerCase endsWith s".$extension")
            .map(_.getAbsolutePath)
            .toList
        case _ =>
          List.empty
      }
    }

  }

}
