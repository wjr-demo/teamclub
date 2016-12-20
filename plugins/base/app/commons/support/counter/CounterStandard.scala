package commons.support.counter

trait CounterStandard[T] {
  def create(name: String, counterType: String, initialValue: Int): Boolean

  def create(name: String, initialValue: Int): Boolean

  def remove(name: String): Boolean

  def exists(name: String): Boolean

  def get(name: String): T

  def set(name: String, value: Int): T

  def incr(name: String): T

  def incr(name: String, value: Int): T

  def decr(name: String): T

  def decr(name: String, value: Int): T
}