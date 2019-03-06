package org.design.patterns.functional

import org.design.patterns.functional.pimp.model.Person

package object pimp {

  implicit class StringExtension(val s: String) extends AnyVal {

    def isAllUpperCase: Boolean =
      (0 until s.length).forall(s.charAt(_).isUpper)

  }

  implicit class PersonSeqExtension(val seq: Iterable[Person]) extends AnyVal {

    def saveToDatabase(): Unit = seq.foreach { person =>
      System.out.println(s"Saved: $person to the database.")
    }
  }

}
