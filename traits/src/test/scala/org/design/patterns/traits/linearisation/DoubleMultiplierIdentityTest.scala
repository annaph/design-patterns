package org.design.patterns.traits.linearisation

import org.scalatest.{FlatSpec, Matchers}

class DoubleMultiplierIdentityTest extends FlatSpec with Matchers {

  val instance = new DoubleMultiplierIdentityClass

  class DoubleMultiplierIdentityClass extends DoubleMultiplierIdentity

  "identity" should "return 2 * 1" in {
    instance.identity should equal(2)
  }

}
