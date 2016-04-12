name := """template"""

organization := "org.fuscus"

version := "1.0-SNAPSHOT"

lazy val infra = (project in file("modules/infra"))
  .settings(BuildSettings.infra)

lazy val domain = (project in file("modules/domain"))
  .settings(BuildSettings.domain)
  .dependsOn(infra)

lazy val appli = (project in file("modules/appli"))
  .settings(BuildSettings.appli)
  .dependsOn(domain, infra)

lazy val main = (project in file("."))
  .enablePlugins(DockerPlugin)
  .settings(BuildSettings.main)
  .aggregate(domain, infra, appli)
  .dependsOn(domain, infra, appli)




