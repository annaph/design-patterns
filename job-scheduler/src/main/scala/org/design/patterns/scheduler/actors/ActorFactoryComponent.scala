package org.design.patterns.scheduler.actors

import org.design.patterns.scheduler.config.app.AppConfigComponent
import org.design.patterns.scheduler.dao.DaoServiceComponent

trait ActorFactory {

  def createMasterActor(): Master

  def createWorkerActor(): Worker

}

trait ActorFactoryComponent {
  self: AppConfigComponent
    with DaoServiceComponent =>

  val actorFactory: ActorFactory

  class ActorFactoryImpl extends ActorFactory {

    override def createMasterActor(): Master =
      new Master(appConfigService.workers, this)

    override def createWorkerActor(): Worker =
      new Worker(daoService)

  }

}
