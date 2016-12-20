package commons.support.counter

import play.Logger
import play.libs.F

class CounterStandardImpl extends CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]] {
  private var logger: Logger.ALogger = Logger.of(classOf[CounterStandardImpl])
  private var counter: CounterStandard[CounterEntity] = null

  def this(counter: CounterStandard[CounterEntity]) {
    this()
    this.counter = counter
  }

  def next(name: String, format: F.Function[CounterEntity, String]): String = {
    val entry: CounterEntity = counter.incr(name)
    if (entry != null) {
      try {
        if (format == null) String.valueOf(entry.counter) else format.apply(entry)
      }
      catch {
        case e: Throwable => {
          logger.error("next format", e)
          null
        }
      }
    }else {
      null
    }

  }

  def create(name: String, counterType: String, initialValue: Int): Boolean = {
    counter.create(name, counterType, initialValue)
  }

  def create(name: String, initialValue: Int): Boolean = {
    counter.create(name, initialValue)
  }

  def remove(name: String): Boolean = {
    counter.remove(name)
  }

  def exists(name: String): Boolean = {
    counter.exists(name)
  }

  def set(name: String, value: Int): Int = {
    counter.set(name, value)
    value
  }

  def incr(name: String): Int = {
    val entry: CounterEntity = counter.incr(name)
    if (entry == null) 0 else entry.counter
  }

  def incr(name: String, value: Int): Int = {
    val entry: CounterEntity = counter.incr(name, value)
    if (entry == null) 0 else entry.counter
  }

  def decr(name: String): Int = {
    val entry: CounterEntity = counter.decr(name)
    if (entry == null) 0 else entry.counter
  }

  def decr(name: String, value: Int): Int = {
    val entry: CounterEntity = counter.decr(name, value)
    if (entry == null) 0 else entry.counter
  }

  def getCounterStandard: CounterStandard[CounterEntity] = {
    counter
  }

  def get(name: String): Int = {
    val entry: CounterEntity = counter.get(name)
    if (entry == null) 0 else entry.counter
  }
}