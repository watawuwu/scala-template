import sbt._

object Dependencies {

  val slickV = "3.1.1"
  val specs2V = "3.7"

  val common = Seq(
    "com.eaio.uuid" % "uuid" % "3.2",
    "com.typesafe" % "config" % "1.3.0",
    "org.mockito" % "mockito-all" % "1.9.5",
    "org.specs2" %% "specs2-core" % specs2V % "test",
    "org.specs2" %% "specs2-mock" % specs2V % "test")

  val db = Seq(
    "mysql" %  "mysql-connector-java" % "6.0.2",
    "com.typesafe.slick" %% "slick" % slickV,
    "com.typesafe.slick" %% "slick-hikaricp" % slickV,
    "com.typesafe.slick" %% "slick-codegen" % slickV,
    "com.amazonaws" % "aws-java-sdk" % "1.10.69")

  val main = common ++ Seq(
    "org.codehaus.janino" % "janino" % "2.7.8")

  val infra = common ++ db ++ Seq(
    "io.backchat.inflector" %% "scala-inflector" % "1.3.5")

  val domain = common ++ db ++ Seq.empty

  val appli = common ++ db ++ Seq.empty
}
