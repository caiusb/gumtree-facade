import sbt._

name := "gumtree-facade"

organization := "com.brindescu"

version := "0.5.5"

scalaVersion := "2.11.7"

EclipseKeys.withSource := true

addCommandAlias("idea", "update-classifiers; update-sbt-classifiers; gen-idea sbt-classifiers")

libraryDependencies ++= Seq(
  "com.github.gumtreediff" % "gumtree-all" % "2.0.0",
  "com.github.scopt" %% "scopt" % "3.3.0",
  "com.brindescu" %% "cdt-facade" % "0.1",
  "com.brindescu" %% "jdtfacade" % "0.7"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

crossScalaVersions := Seq("2.10.6", "2.11.7")

resolvers += "Me" at "http://releases.ivy.brindescu.com"

lazy val versionReport = TaskKey[String]("version-report")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

publishTo := {
  val prefix = if (isSnapshot.value) "snapshots" else "releases"
  Some("Personal" at "s3://"+prefix+".ivy.brindescu.com")
}
