package commons

import java.math.BigInteger
import java.security.MessageDigest

import org.apache.commons.codec.digest.DigestUtils

/**
 * Created by zhangmeng on 16-12-22.
 */
object Libs {
  object CachePrefix {
    val LOGIN = "login_"
  }
}

object Test extends App {
  println(DigestUtils.md5Hex("admin123"))
}
