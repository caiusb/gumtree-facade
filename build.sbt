import sbt._

name := "gumtree-facade"

organization := "com.brindescu"

version := "0.7"

EclipseKeys.withSource := true

addCommandAlias("idea", "update-classifiers; update-sbt-classifiers; gen-idea sbt-classifiers")

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "com.github.gumtreediff" % "gumtree-all" % "2.0.0",
  "com.github.scopt" %% "scopt" % "3.5.+",
  "com.brindescu" %% "cdt-facade" % "0.1",
  "com.brindescu" %% "jdtfacade" % "0.8"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.+" % "test"

crossScalaVersions := Seq("2.10.6", "2.11.7", "2.12.0")

resolvers ++= Seq("Me" at "http://releases.ivy.brindescu.com",
  Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)
)

lazy val versionReport = TaskKey[String]("version-report")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

publishTo := {
  val prefix = if (isSnapshot.value) "snapshots" else "releases"
  Some("Personal" at "s3://"+prefix+".ivy.brindescu.com")
}
