package libs.backend

import java.nio.ByteBuffer
import java.util.UUID

import commons.support.counter.Counter

/**
  * Created by Administrator on 2016/12/25.
  */
object Libs {
  def geneOrganNum(): Int = {
    val organNum = Counter.create("organNum")
    organNum.incr(1)
    15121000 + organNum.get
  }

  def genePicId = {
    UUID.randomUUID().toString
  }

}
