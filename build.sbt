name := "kafka_log_aggregator"

version := "0.1"

scalaVersion := "2.12.6"

val workaround = {
  sys.props += "packaging.type" -> "jar"
  ()
}

libraryDependencies += "javax.ws.rs" % "javax.ws.rs-api" % "2.1"
libraryDependencies += "org.apache.kafka" %	"kafka-streams" %	"2.0.0" exclude("org.slf4j", "slf4j-simple")
libraryDependencies += "org.apache.kafka" % "kafka-clients" %	"2.0.0" exclude("org.slf4j", "slf4j-simple")
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.0.0" exclude("org.slf4j", "slf4j-simple")


// Logback with slf4j facade
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.0.13"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"
