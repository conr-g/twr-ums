name := """user-management-service"""
organization := "com.ngc.thewaitingroom"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.projectlombok" % "lombok" % "1.18.4",
  guice,
  javaJpa,
  evolutions,
  "net.jodah" % "failsafe" % "1.0.3",
  "org.jooq" % "jooq" % "3.11.9",
  "org.mongodb" % "mongodb-driver-sync" % "3.10.1",
  "io.swagger" % "swagger-core" % "1.5.22",
  "com.google.code.gson" % "gson" % "2.8.5",
  "org.mockito" % "mockito-core" % "2.23.4" % Test,
  "org.assertj" % "assertj-core" % "3.11.1" % Test
)