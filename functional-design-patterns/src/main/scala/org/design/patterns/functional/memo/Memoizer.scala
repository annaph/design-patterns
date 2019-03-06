package org.design.patterns.functional.memo

import scala.collection.mutable

trait Memoizer {

  def memo[X, Y](f: X => Y): X => Y = {
    val cache = mutable.Map.empty[X, Y]
    x => cache getOrElseUpdate(x, f(x))
  }

}
