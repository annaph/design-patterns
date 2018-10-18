name := "behavioral-design-patterns"

organization := "org.design.patterns"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.github.tototoshi" %% "scala-csv" % "1.3.5",
  "org.json4s" %% "json4s-jackson" % "3.5.4")

fork := true

connectInput in run := true
