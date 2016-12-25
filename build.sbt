name := "platform"

organization in ThisBuild := "com.zhangmeng"

val commonSetting = Seq(
    unmanagedResources in Compile <<= (
      javaSource in Compile,
      classDirectory in Compile,
      unmanagedResources in Compile
      ) map {
      (app, classes, resources) =>
        IO.copyDirectory(app / "views", classes / "views", overwrite = true)
        resources
    },
    generateReverseRouter := false
)

lazy val root = project.in(file("."))

lazy val base = project.in(file("plugins/base"))
  .settings(playScalaSettings: _*)
  .settings(playPlugin := true)

lazy val models = project.in(file("plugins/models"))
  .settings(playJavaSettings: _*)
  .settings(playPlugin := true)
  .dependsOn(base)
  .aggregate(base)

lazy val backend = project.in(file("plugins/backend"))
  .settings(playJavaSettings ++ commonSetting: _*)
  .dependsOn(base, models)
  .aggregate(base, models)

lazy val dongkaiyue = project.in(file("plugins/dongkaiyue"))
  .settings(playJavaSettings ++ commonSetting: _*)
  .dependsOn(backend)
  .aggregate(backend)

lazy val tester = project.in(file("plugins/tester"))
  .settings(playJavaSettings ++ commonSetting: _*)
  .dependsOn(base, models)
  .aggregate(base, models)


play.Project.playScalaSettings