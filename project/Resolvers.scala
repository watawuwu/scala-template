import sbt._

object Resolvers {
  lazy val common = Seq(
    "twttr.com" at "http://maven.twttr.com/")

  val main: Seq[Resolver] = common
  val infra: Seq[Resolver] = common
  val domain: Seq[Resolver] = common
  val appli: Seq[Resolver] = common
}

