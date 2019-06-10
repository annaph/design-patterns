package org.design.patterns.scheduler.registry

import org.design.patterns.scheduler.actors.{ActorFactory, ActorFactoryComponent}
import org.design.patterns.scheduler.config.app.AppConfigComponent
import org.design.patterns.scheduler.dao._
import org.design.patterns.scheduler.io.IOServiceComponent
import org.design.patterns.scheduler.services.JobConfigReaderServiceComponent

object ComponentRegistry extends AppConfigComponent
  with IOServiceComponent
  with JobConfigReaderServiceComponent
  with DatabaseServiceComponent
  with MigrationServiceComponent
  with DaoServiceComponent
  with ActorFactoryComponent {

  override val appConfigService: ComponentRegistry.AppConfigService =
    new AppConfigService

  override val ioService: ComponentRegistry.IOService =
    new IOService

  override val jobConfigReaderService: ComponentRegistry.JobConfigReaderService =
    new JobConfigReaderService

  override val databaseService: DatabaseService =
    new H2DatabaseService

  override val migrationService: ComponentRegistry.MigrationService =
    new MigrationService

  override val daoService: DaoService =
    new DaoServiceImpl

  override val actorFactory: ActorFactory =
    new ActorFactoryImpl

}
