name := """play2MaraFromQ3"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
  "org.jumpmind.symmetric.jdbc" % "mariadb-java-client" % "1.1.1",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "org.scalikejdbc" %% "scalikejdbc"       % "2.2.3",
  "com.h2database"  %  "h2"                % "1.4.185",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.2"
)
