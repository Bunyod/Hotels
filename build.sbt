name := "MyDissertation"

version := "1.0"

lazy val `mydissertation` = (project in file(".")).enablePlugins(PlayScala)

herokuAppName in Compile := "hotel-booking-7788"


scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0" withJavadoc() withSources(),
  "com.typesafe.play" %% "play-slick" % "0.8.0" withJavadoc() withSources(),
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  // Logging
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "ch.qos.logback" % "logback-core" % "1.0.13",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.5",
  // Auth
  "jp.t2v" %% "play2-auth" % "0.12.0" withSources() withJavadoc(),
  "jp.t2v" %% "play2-auth-test" % "0.12.0" % "test" withSources() withJavadoc(),
  "jp.t2v" %% "stackable-controller" % "0.4.0" withSources() withJavadoc()
)

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )