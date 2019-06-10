name := "job-scheduler"

organization := "org.design.patterns"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3",
  "org.json4s" %% "json4s-native" % "3.6.5",
  "org.json4s" %% "json4s-jackson" % "3.6.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.21",
  "com.h2database" % "h2" % "1.4.199",
  "org.slf4j" % "slf4j-log4j12" % "1.7.25",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.scalatest" %% "scalatest" % "3.0.7" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test)

fork := true
