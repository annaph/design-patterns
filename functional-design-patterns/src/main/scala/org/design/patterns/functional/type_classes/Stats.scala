package org.design.patterns.functional.type_classes

object Stats {

  def median[T: Number](xs: Vector[T]): T =
    xs(xs.size / 2)

  def stddev[T: Number](xs: Vector[T]): T =
    implicitly[Number[T]] sqrt variance(xs)

  def variance[T: Number](xs: Vector[T]): T = {
    val simpleMean = mean(xs)
    val sqDiff = xs.map { x =>
      val diff = implicitly[Number[T]] minus(x, simpleMean)
      implicitly[Number[T]] multiply(diff, diff)
    }

    mean(sqDiff)
  }

  def mean[T: Number](xs: Vector[T]): T =
    implicitly[Number[T]] divide(
      xs reduce (implicitly[Number[T]].plus(_, _)),
      xs.size)

}
