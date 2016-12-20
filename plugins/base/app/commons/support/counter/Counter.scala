package commons.support.counter

import play.libs.F

object Counter {
  def create(name: String, initialValue: Int, loopType: Int, format: F.Function[CounterEntity, String]): Counter = {
    var _initialValue = initialValue
    var _loopType = loopType
    val counter: CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]] = CounterTypeStandardFactory.create
    _initialValue = if (initialValue > 0) initialValue else 0
    _loopType = if (loopType > 0) loopType else 0
    val success: Boolean = counter.create(name, _initialValue)
    if (!success) {
      throw new RuntimeException("create counter [" + name + "] failure")
    }
    new Counter(name, format, counter)
  }

  def create(name: String, initialValue: Int, format: String): Counter = {
    create(name, initialValue, 0, CounterTypeStandardFactory.createFormat(format))
  }

  def create(name: String, initialValue: Int, loopType: Int): Counter = {
    create(name, initialValue, loopType, CounterTypeStandardFactory.createFormat("yyyyMMdd000000"))
  }

  def create(name: String, initialValue: Int): Counter = {
    create(name, initialValue, 0)
  }

  def create(name: String): Counter = {
    create(name, 0)
  }
}

class Counter extends ICounter {
  private var counter: CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]] = null
  private var name: String = null
  private var format: F.Function[CounterEntity, String] = null

  private def this(name: String, format: F.Function[CounterEntity, String], counter: CounterTypeStandard[CounterEntity, CounterStandard[CounterEntity]]) {
    this()
    this.name = name
    this.format = format
    this.counter = counter
  }

  def get: Int = {
    counter.get(name)
  }

  def set(value: Int): Int = {
    counter.set(name, value)
  }

  def incr: Int = {
    counter.incr(name)
  }

  def incr(value: Int): Int = {
    counter.incr(name, value)
  }

  def decr: Int = {
    counter.decr(name)
  }

  def decr(value: Int): Int = {
    counter.decr(name, value)
  }

  def next: String = {
    counter.next(name, format)
  }
}