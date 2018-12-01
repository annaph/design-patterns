package org.design.patterns.deep.theory.monoids

trait Monoid[T] {

  def zero: T

  def op(l: T, r: T): T

}
