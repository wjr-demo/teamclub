package commons.kaptcha

/**
 * Created by zhangmeng on 16-12-22.
 */
import java.io.ByteArrayOutputStream
import java.util.Properties
import javax.imageio.ImageIO

import com.google.code.kaptcha.impl.DefaultKaptcha
import com.google.code.kaptcha.util.Config
import org.apache.commons.codec.binary.Base64
import play.api.Play.current
import play.api.{Application, Logger, Play, Plugin}
/**
 * Created by Adel on 14.01.2015.
 */
class PlayKaptcha (app: Application) extends Plugin {
  val logger = Logger(PlayKaptcha.getClass)

  val captchaPro = {
    val pro = new DefaultKaptcha()
    //Set the configs of Kaptcha
    val props = new Properties()
    val borderProp = "kaptcha.output.border"
    //sets the border for the captcha. Default "no" border //todo load loop through all the list of Kaptcha props
    props.setProperty(borderProp, Play.configuration.getString(borderProp).getOrElse("no"))
    val config = new Config(props)
    pro.setConfig(config)
    pro
  }

  /**
   * Token expiration time in seconds, default is 12 hours
   */
  lazy val tokenExpiration= app.configuration.getInt("kaptcha.token.expiration").getOrElse(43200)

  /**
   * Image type: png, jpg etc... default is png
   */
  lazy val imageType = app.configuration.getString("kaptcha.image.type").getOrElse("png")


}

object PlayKaptcha {
  private val logger = play.api.Logger(this.getClass)

  val instance: PlayKaptcha =
    Play.current.plugin[PlayKaptcha].
      getOrElse(throw new RuntimeException("PlayKaptcha plugin not loaded"))



  /**
   *
   * @return (the image encoded in base 64, and the corresponding text)
   */
  def  captchaAsBase64: (String,String) = {

    val text =  instance.captchaPro.createText()
    logger.debug("Captcha:"+text)
    val img = instance.captchaPro.createImage(text)
    val baos = new ByteArrayOutputStream()
    try {
      ImageIO.write(img, instance.imageType, baos)
      baos.flush()
      val encodedImage = new Base64().encodeToString(baos.toByteArray())
      (encodedImage,text)
    }finally {
      baos.close()
    }

  }

}