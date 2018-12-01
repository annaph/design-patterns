package org.design.patterns.deep.theory

package object monoids {

  val intAddition: Monoid[Int] = new Monoid[Int] {
    val zero: Int = 0

    override def op(l: Int, r: Int): Int =
      l + r

  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    val zero: Int = 1

    override def op(l: Int, r: Int): Int =
      l * r

  }

  val stringConcatenation: Monoid[String] = new Monoid[String] {
    val zero: String = ""

    override def op(l: String, r: String): String =
      l + r
  }

  def compose[T, Y](a: Monoid[T], b: Monoid[Y]): Monoid[(T, Y)] =
    new Monoid[(T, Y)] {
      val zero: (T, Y) = a.zero -> b.zero

      override def op(l: (T, Y), r: (T, Y)): (T, Y) = (l, r) match {
        case ((l1, l2), (r1, r2)) =>
          a.op(l1, r1) -> b.op(l2, r2)
      }

    }

  def mapMerge[K, V](m: Monoid[V]): Monoid[Map[K, V]] =
    new Monoid[Map[K, V]] {

      override def zero: Map[K, V] =
        Map.empty

      override def op(l: Map[K, V], r: Map[K, V]): Map[K, V] =
        (l.keySet ++ r.keySet).foldLeft(zero) {
          case (res, key) =>
            res updated(key, m op(l getOrElse(key, m.zero), r getOrElse(key, m.zero)))
        }

    }

  object MonoidOperations {

    def fold[T](list: List[T], m: Monoid[T]): T =
      foldMap(list, m)(identity)

    def foldMap[T, Y](list: List[T], m: Monoid[Y])(f: T => Y): Y =
      list.foldLeft(m.zero) {
        case (y, t) => m op(y, f(t))
      }

    def foldPar[T](list: List[T], m: Monoid[T]): T =
      foldMapPar(list, m)(identity)

    def foldMapPar[T, Y](list: List[T], m: Monoid[Y])(f: T => Y): Y =
      list.par.foldLeft(m.zero) {
        case (y, t) => m op(y, f(t))
      }

    def balancedFold[T, Y](seq: IndexedSeq[T], m: Monoid[Y])(f: T => Y): Y = seq.length match {
      case 0 =>
        m.zero
      case 1 =>
        m op(m.zero, f(seq(0)))
      case _ =>
        val (left, right) = seq splitAt (seq.length / 2)
        m op(balancedFold(left, m)(f), balancedFold(right, m)(f))
    }

  }

}
