package org.design.patterns.applications

import scalaz.Monoid

package object monoids {

  val stringConcatenation: Monoid[String] = new Monoid[String] {

    override def zero: String = ""

    override def append(f1: String, f2: => String): String =
      f1 + f2

  }

}
