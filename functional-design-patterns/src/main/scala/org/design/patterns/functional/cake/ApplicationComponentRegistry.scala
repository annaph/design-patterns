package org.design.patterns.functional.cake

object ApplicationComponentRegistry
  extends UserComponent
    with DaoComponent
    with MigrationComponent
    with DatabaseComponent {

  override val userService: ApplicationComponentRegistry.UserService = new UserService
  override val daoService: ApplicationComponentRegistry.DaoService = new DaoService
  override val migrationService: ApplicationComponentRegistry.MigrationService = new MigrationService
  override val databaseService: ApplicationComponentRegistry.H2DatabaseService =
    new H2DatabaseService("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "")

}
