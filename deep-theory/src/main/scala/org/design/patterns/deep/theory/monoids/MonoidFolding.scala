package org.design.patterns.deep.theory.monoids

object MonoidFolding extends App {

  val strings = List("This is\n", "a list of\n", "strings!")
  val numbers = List(1, 2, 3, 4, 5, 6)

  System.out.println(s"Left folded:\n ${strings.foldLeft(stringConcatenation.zero)(stringConcatenation.op)}")
  System.out.println(s"Right folded:\n ${strings.foldRight(stringConcatenation.zero)(stringConcatenation.op)}")
  System.out.println(s"6! is: ${numbers.foldLeft(intMultiplication.zero)(intMultiplication.op)}")

}

object MonoidFoldingGeneric extends App {

  val strings = List("This is\n", "a list of\n", "strings!")
  val numbers = List(1, 2, 3, 4, 5, 6)

  System.out.println(s"Left folded:\n ${MonoidOperations.fold(strings, stringConcatenation)}")
  System.out.println(s"Right folded:\n ${MonoidOperations.fold(strings, stringConcatenation)}")
  System.out.println(s"6! is: ${MonoidOperations.fold(numbers, intMultiplication)}")

}

object MonoidBalancedFold extends App {

  val numbers = Array(1, 2, 3, 4)
  System.out.println(s"4! is: ${MonoidOperations.balancedFold(numbers, intMultiplication)(identity)}")

}

object MonoidFoldingGenericPar extends App {

  val strings = List("This is\n", "a list of\n", "strings!")
  val numbers = List(1, 2, 3, 4, 5, 6)

  System.out.println(s"Left folded:\n ${MonoidOperations.foldPar(strings, stringConcatenation)}")
  System.out.println(s"Right folded:\n ${MonoidOperations.foldPar(strings, stringConcatenation)}")
  System.out.println(s"6! is: ${MonoidOperations.foldPar(numbers, intMultiplication)}")

}

object ComposeMonoid extends App {

  val numbers = Array(1, 2, 3, 4, 5, 6)
  val sumAndProduct: Monoid[(Int, Int)] = compose(intAddition, intMultiplication)

  System.out.println(s"The sum and product is: ${MonoidOperations.balancedFold(numbers, sumAndProduct)(i => i -> i)}")

}

object FeatureCounting extends App {

  val features = Array("hello", "features", "for", "ml", "hello", "for", "features")
  val counterMonoid: Monoid[Map[String, Int]] = mapMerge(intAddition)

  System.out.println(s"The features are: ${MonoidOperations.balancedFold(features, counterMonoid)(i => Map(i -> 1))}")

}

object FeatureCountingOffOne extends App {

  val features = Array("hello", "features", "for", "ml", "hello", "for", "features")
  val featuresMap = features.foldLeft(Map.empty[String, Int]) {
    case (res, feature) =>
      res updated(feature, res.getOrElse(feature, 0) + 1)
  }

  System.out.println(s"The features are: $featuresMap")

}
