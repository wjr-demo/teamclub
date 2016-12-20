package commons.support.counter

import java.text.{DecimalFormat, Format, SimpleDateFormat}
import java.util.Date

import play.libs.F

object CounterTypeStandardFactory {
  private var counter: CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]] = null

  def create: CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]] = {
    if (counter == null) {
      counter = new CounterStandardImpl(new DatabaseCounter)
    }
    return counter
  }

  def createFormat(format: String): F.Function[CounterEntity, String] = {
    var nf: Format = null
    var df: Format = null
    var i: Int = format.indexOf('0')
    var nc: Int = format.length
    if (i == 0) {
      nf = new DecimalFormat(format)
    }
    else {
      val dateF: String = format.substring(0, i).trim
      val numberF: String = format.substring(i, format.length).trim
      nc = numberF.length
      df = new SimpleDateFormat(dateF)
      nf = new DecimalFormat(numberF)
    }
    val ch: Array[Char] = new Array[Char](nc + 1)
    ch(0) = '1'
    var j = 1
    while(j <= nc) {
      ch(j) = '0'
      j = j + 1
    }
    val numberFormat: Format = nf
    val dateFormat: Format = df
    val loopValue: Int = Integer.valueOf(new String(ch))
    return new F.Function[CounterEntity, String]() {
      @throws(classOf[Throwable])
      def apply(entity: CounterEntity): String = {
        val ns: String = numberFormat.format(entity.counter % loopValue)
        if (dateFormat == null) {
          return ns
        }
        val ds: String = dateFormat.format(new Date(entity.updateAt * 1000l))
        return ds + ns
      }
    }
  }
}