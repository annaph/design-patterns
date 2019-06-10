package org.design.patterns.scheduler.dao

import java.sql.Connection

import javax.sql.DataSource
import org.design.patterns.scheduler.config.app.AppConfigComponent
import org.h2.jdbcx.JdbcConnectionPool

trait DatabaseService {
  val driver: String
  val connectionString: String
  val username: String
  val password: String
  val ds: DataSource

  def obtainConnection(): Connection =
    ds.getConnection()

}

trait DatabaseServiceComponent {
  self: AppConfigComponent =>

  val databaseService: DatabaseService

  class H2DatabaseService extends DatabaseService {

    override val driver = "org.h2.driver"

    override val connectionString: String =
      appConfigService.dbConnectionString

    override val username: String =
      appConfigService.dbUsername

    override val password: String =
      appConfigService.dbPassword

    override val ds: DataSource =
      JdbcConnectionPool.create(connectionString, username, password)

  }

}
