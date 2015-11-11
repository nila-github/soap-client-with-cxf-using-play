name := """soap-client-with-cxf-using-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

val cxfVersion: String = "3.1.4"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,

  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % cxfVersion,
  "org.apache.cxf" % "cxf-rt-transports-http" % cxfVersion,
  "org.springframework" % "spring-context" % "4.2.2.RELEASE",

  // Test dependencies
  "junit" % "junit" % "4.12" % "test",
  "org.assertj" % "assertj-core" % "3.2.0" % "test",
  "org.mockito" % "mockito-core" % "1.10.19" % "test"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// CXF wsdl2java configuration
Seq(cxf.settings: _*)
cxf.cxfVersion := cxfVersion
cxf.wsdls := Seq(
  cxf.Wsdl((resourceDirectory in Compile).value / "wsdls/globalweather.wsdl", Seq("-mark-generated", "-p", "com.global.weather"), "globalweather")
)