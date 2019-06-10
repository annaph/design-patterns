package org.design.patterns.scheduler

import org.design.patterns.scheduler.actors.{ActorFactory, ActorFactoryComponent}
import org.design.patterns.scheduler.config.app.AppConfigComponent
import org.design.patterns.scheduler.dao._
import org.design.patterns.scheduler.io.IOServiceComponent
import org.design.patterns.scheduler.services.JobConfigReaderServiceComponent
import org.mockito.Mockito.{spy, when}
import org.scalatestplus.mockito.MockitoSugar

trait TestEnvironment extends AppConfigComponent
  with IOServiceComponent
  with JobConfigReaderServiceComponent
  with DatabaseServiceComponent
  with MigrationServiceComponent
  with DaoServiceComponent
  with ActorFactoryComponent
  with MockitoSugar {

  override val appConfigService: AppConfigService =
    spy(new AppConfigService)

  override val ioService: IOService =
    mock[IOService]

  override val jobConfigReaderService =
    mock[JobConfigReaderService]

  override val databaseService: DatabaseService =
    mock[DatabaseService]

  override val migrationService: MigrationService =
    mock[MigrationService]

  override val daoService: DaoService =
    mock[DaoService]

  override val actorFactory: ActorFactory =
    mock[ActorFactory]

  when(appConfigService.configPath)
    .thenReturn(getClass.getResource("/").getPath)

}
