name := "real-life-applications"

organization := "org.design.patterns"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.27",
  "org.scalaz" %% "scalaz-effect" % "7.2.27",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.27-scalacheck-1.14" % Test)

connectInput in run := true

fork := true
