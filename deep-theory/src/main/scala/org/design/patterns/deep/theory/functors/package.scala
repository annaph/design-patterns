package org.design.patterns.deep.theory

package object functors {

  val listFunctor: Functor[List] = new Functor[List] {

    override def map[T, Y](l: List[T])(f: T => Y): List[Y] =
      l map f

  }

}
