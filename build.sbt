import sbt._

EclipseKeys.withSource := true

addCommandAlias("idea", "update-classifiers; update-sbt-classifiers; gen-idea sbt-classifiers")

libraryDependencies ++= Seq(
  "com.github.gumtreediff" % "gumtree-all" % "2.0.0",
  "com.github.scopt" %% "scopt" % "3.3.0"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

crossScalaVersions := Seq("2.10.6", "2.11.7")

lazy val root = (project in file(".")).
  settings(
    name := "gumtree-facade",
    organization := "com.brindescu",
    version := "0.3"
  )

lazy val versionReport = TaskKey[String]("version-report")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
