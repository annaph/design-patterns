name := "structural-design-patterns"

organization := "org.design.patterns"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-log4j12" % "1.7.25",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "commons-codec" % "commons-codec" % "1.11",
  "org.json4s" %% "json4s-jackson" % "3.5.4")

fork := true
