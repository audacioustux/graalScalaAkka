val scala3Version = "3.0.0"
val AkkaVersion = "2.6.14"

lazy val root = project
  .in(file("."))
  // .enablePlugins(NativeImagePlugin)
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "scala3-simple",
    version := "0.1.0",
    // nativeImageInstalled := true,
    graalVMNativeImageOptions ++= List("--allow-incomplete-classpath", "--initialize-at-build-time", "--no-fallback", "--language:js"),
    
    Compile / mainClass := Some("HelloWorldMain"),

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.graalvm.sdk" % "graal-sdk" % "21.1.0" % "provided",
    libraryDependencies += ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test).cross(CrossVersion.for3Use2_13),
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"  
)
