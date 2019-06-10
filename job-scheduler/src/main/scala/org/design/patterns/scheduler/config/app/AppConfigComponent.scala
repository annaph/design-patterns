package org.design.patterns.scheduler.config.app

import com.typesafe.config.ConfigFactory

trait AppConfigComponent {

  val appConfigService: AppConfigService

  class AppConfigService {

    private val conf = ConfigFactory.load()

    private val jobSchedulerConf = conf getConfig "job-scheduler"
    private val dbConf = jobSchedulerConf getConfig "db"

    val configPath: String = jobSchedulerConf getString "config-path"
    val configExtension: String = jobSchedulerConf getString "config-extension"
    val workers: Int = jobSchedulerConf getInt "workers"

    val dbConnectionString: String = dbConf getString "connection-string"
    val dbUsername: String = dbConf getString "username"
    val dbPassword: String = dbConf getString "password"

  }

}
