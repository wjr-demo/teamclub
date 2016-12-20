package commons.support.counter

import java.util.Calendar

object CounterUtils {
  def loop(count: Int, loopType: Int, updateAt: Int): Int = {
    if (loopType > 0) {
      val ca: Calendar = Calendar.getInstance
      ca.setTimeInMillis(System.currentTimeMillis)
      val nf: Int = ca.get(loopType)
      val of: Int = ca.get(loopType)
      if (nf == of) count else 1
    }
    else {
      count
    }
  }
}