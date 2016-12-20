package commons.support.counter

import play.libs.F

trait CounterTypeStandard[T, A <: CounterStandard[T]] {
  def getCounterStandard: A

  def next(name: String, format: F.Function[T, String]): String

  def create(name: String, counterType: String, initialValue: Int): Boolean

  def create(name: String, initialValue: Int): Boolean

  def remove(name: String): Boolean

  def exists(name: String): Boolean

  def get(name: String): Int

  def set(name: String, value: Int): Int

  def incr(name: String): Int

  def incr(name: String, value: Int): Int

  def decr(name: String): Int

  def decr(name: String, value: Int): Int
}