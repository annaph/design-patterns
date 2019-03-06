package org.design.patterns.functional.cake

import org.scalatest.mockito.MockitoSugar

trait TestEnvironment
  extends UserComponent
    with DaoComponent
    with MigrationComponent
    with DatabaseComponent
    with MockitoSugar {

  override val userService: UserService = mock[UserService]
  override val daoService: DaoService = mock[DaoService]
  override val migrationService: MigrationService = mock[MigrationService]
  override val databaseService: DatabaseService = mock[DatabaseService]

}
