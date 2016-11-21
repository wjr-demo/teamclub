package scalatest.xxx

import play.api.libs.Codecs

/**
* Created by zhangmeng on 16-8-19.
*/

object BB extends App{
  println(Codecs.md5("admin123".getBytes))
}