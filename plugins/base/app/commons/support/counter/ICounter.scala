package commons.support.counter

trait ICounter {
  def get: Int

  def set(value: Int): Int

  def incr: Int

  def incr(value: Int): Int

  def decr: Int

  def decr(value: Int): Int

  def next: String
}