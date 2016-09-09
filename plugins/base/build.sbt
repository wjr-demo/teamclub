libraryDependencies ++= Seq(
  jdbc,
  cache,
  javaCore,
  javaEbean,
  "com.typesafe.play" % "routes-compiler_2.10" % "2.2.1",
  "com.github.mumoshu" %% "play2-memcached" % "0.4.0",
  "com.lmax" % "disruptor" % "3.3.2",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "org.freemarker" % "freemarker" % "2.3.19",
  "org.springframework" % "spring-context" % "3.2.5.RELEASE",
  "org.springframework" % "spring-aop" % "3.2.5.RELEASE",
  "org.springframework" % "spring-aspects" % "3.2.5.RELEASE",
  "org.springframework" % "spring-test" % "3.2.5.RELEASE",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "net.coobird" % "thumbnailator" % "0.4.8", // 图片缩放
  "com.google.zxing" % "core" % "2.2" //二维码
)

resolvers += "Spy Repository" at "http://files.couchbase.com/maven2"