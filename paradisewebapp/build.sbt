val ScalatraVersion = "2.6.5"

organization := "name.piotrszul"

name := "ParadiseWebApp"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "provided",
  "net.liftweb" %% "lift-json" % "3.3.0",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "junit" % "junit" % "4.12" % "test",
  "org.scalamock" %% "scalamock" % "4.1.0" % "test",
  "org.neo4j.test" % "neo4j-harness" % "3.5.3" % "test"
)

enablePlugins(ScalatraPlugin)
