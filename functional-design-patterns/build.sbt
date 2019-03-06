name := "functional-design-patterns"

organization := "org.design.patterns"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

scalacOptions ++= Seq(
  "-language:implicitConversions",
  "-language:reflectiveCalls"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.27",
  "com.h2database" % "h2" % "1.4.197",
  "commons-codec" % "commons-codec" % "1.12",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.mockito" % "mockito-core" % "2.23.4" % Test)

fork := true
