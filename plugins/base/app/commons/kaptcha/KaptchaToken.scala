package commons.kaptcha

/**
 * Created by zhangmeng on 16-12-22.
 */

import java.util.UUID

import play.api.Play.current
import play.api.cache.Cache

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * The captcha token. The expiration should be managed by the cache
 * @param uuid
 * @param text
 */
case class KaptchaToken(uuid: String, text: String)

object KaptchaToken {
  def apply(text: String): KaptchaToken = KaptchaToken(UUID.randomUUID().toString, text)
}

object KaptchaService {

  /**
   * @return Future[the image encoded in base 64, and the corresponding text]
   */
  def create(): Future[(KaptchaToken, String)] = {
    val captcha = PlayKaptcha.captchaAsBase64
    val imgUrlB64 = "data:image/png;base64," + captcha._1
    val token = KaptchaToken(captcha._2)
    save(token).map((_, imgUrlB64))
  }

  /**
   * Retrive the token from the cache
   * @param tokenUUID the id of the token
   * @return
   */
  def retrieve(tokenUUID: String): Future[Option[KaptchaToken]] = {
    Future.successful(Cache.getAs[KaptchaToken]("captcha.tokens-" + tokenUUID))
  }

  /**
   * Saves the token in the cache. The expiry is managed by the play cache using the value of configuration
   * variable  kaptcha.token.expiration  in second
   * @param token
   * @return
   */
  def save(token: KaptchaToken): Future[KaptchaToken] = {
    Cache.set("captcha.tokens-" + token.uuid, token, PlayKaptcha.instance.tokenExpiration)
    Future.successful(token)
  }
}
