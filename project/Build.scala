import sbt.Keys._
import sbt._
import org.scalafmt.sbt.ScalaFmtPlugin.autoImport._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.docker._

object BuildSettings {

  val common: Seq[Setting[_]] = Seq(
    scalaVersion := "2.11.8",
    javaOptions ++= Seq("-Xmx2G", "-Xms512M", "-XX:+CMSClassUnloadingEnabled"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint", "-Ywarn-dead-code", "-Ywarn-numeric-widen", "-Ywarn-unused", "-Ywarn-value-discard", "-language:reflectiveCalls"),
    parallelExecution in Test := false,
    doc in Compile <<= target.map(_ / "none"),
    crossPaths := false
  ) ++ reformatOnCompileSettings

  val mainJavaOptions = Seq("-Dconfig.file=test/application.conf", "-Dlogback.configurationFile=test/logback.xml")
  val moduleJavaOptions = Seq("-Dconfig.file=../test/application.conf", "-Dlogback.configurationFile=../test/logback.xml")

  val main: Seq[Setting[_]] = common ++
    Seq(
      resolvers ++= Resolvers.main,
      libraryDependencies ++= Dependencies.main,
      javaOptions in Test ++= mainJavaOptions,
      mappings in Universal ++= {
        val dir = baseDirectory.value / "tool"
          (dir.*** --- dir) pair relativeTo(dir.getParentFile)
      }


      // maintainer in Docker := "watawuwu",
      // dockerBaseImage := "dockerfile/java:oracle-java8",
      // dockerRepository := Some("watawuwu")
    )

  val infra: Seq[Setting[_]] = common ++
    Seq(
      resolvers ++= Resolvers.infra,
      libraryDependencies ++= Dependencies.infra,
      javaOptions in Test ++= moduleJavaOptions
    )

  val domain: Seq[Setting[_]] = common ++
    Seq(
      resolvers ++= Resolvers.domain,
      libraryDependencies ++= Dependencies.domain,
      javaOptions in Test ++= moduleJavaOptions
    )

  val appli: Seq[Setting[_]] = common ++
    Seq(
      resolvers ++= Resolvers.appli,
      libraryDependencies ++= Dependencies.appli,
      javaOptions in Test ++= moduleJavaOptions
    )
}
