package scalatest

import java.io.ByteArrayOutputStream
import java.util.Properties
import javax.imageio.ImageIO

import com.google.code.kaptcha.impl.DefaultKaptcha
import com.google.code.kaptcha.util.Config
import org.apache.commons.codec.binary.Base64
import org.apache.http.client.HttpClient

/**
 * Created by zhangmeng on 16-12-22.
 */
object ImageGene extends App {
  val pro = new DefaultKaptcha()
  val props = new Properties()
  pro.setConfig(new Config(new Properties()))

  val str = pro.createText()
  println(str)

  val img = pro.createImage(str)
  val baos = new ByteArrayOutputStream()
  try {
    ImageIO.write(img, "png", baos)
    baos.flush()
    val encodedImage = new Base64().encodeToString(baos.toByteArray())
    println(encodedImage)
  }finally {
    baos.close()
  }
}


object B {
  def main(args: Array[String]) = {
    println(new String(new Base64().encode(Array[Byte]('A', 'B'))))
  }
}