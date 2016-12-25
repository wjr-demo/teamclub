package libs.backend

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
}
