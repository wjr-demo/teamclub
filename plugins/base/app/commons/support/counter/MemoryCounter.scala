package commons.support.counter

import java.util.concurrent.atomic.AtomicInteger

class MemoryCounter extends CounterStandard[CounterEntity] {
  private var counter: AtomicInteger = new AtomicInteger
  private var name: String = null
  private var createAt: Int = 0
  private var updateAt: Int = 0
  private var loopType: Int = 0
  private var counterType: String = null

  def create(name: String, counterType: String, initialValue: Int): Boolean = {
    counter.set(initialValue)
    true
  }

  def create(name: String, initialValue: Int): Boolean = {
    counter.set(initialValue)
    true
  }

  def remove(name: String): Boolean = {
    false
  }

  def exists(name: String): Boolean = {
    true
  }

  def get(name: String): CounterEntity = {
    null
  }

  def set(name: String, value: Int): CounterEntity = {
    null
  }

  def incr(name: String): CounterEntity = {
    null
  }

  def incr(name: String, value: Int): CounterEntity = {
    null
  }

  def decr(name: String): CounterEntity = {
    null
  }

  def decr(name: String, value: Int): CounterEntity = {
    null
  }

  protected def getEntity: CounterEntity = {
    val entity: CounterEntity = new CounterEntity
    entity
  }
}