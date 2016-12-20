package commons.support.counter

class CounterEntity {
  var id: String = null
  var counter: Int = 0
  var updateAt: Int = 0
  var createAt: Int = 0
  var loopType: Int = 0
  var counterType: String = null
  var updateVersoin: Int = 0

  def getId: String = {
    id
  }

  def setId(id: String) {
    this.id = id
  }

  def getCounter: Int = {
    counter
  }

  def setCounter(counter: Int) {
    this.counter = counter
  }

  def getUpdateAt: Int = {
    updateAt
  }

  def setUpdateAt(updateAt: Int) {
    this.updateAt = updateAt
  }

  def getCreateAt: Int = {
    createAt
  }

  def setCreateAt(createAt: Int) {
    this.createAt = createAt
  }

  def getCounterType: String = {
    counterType
  }

  def setCounterType(counterType: String) {
    this.counterType = counterType
  }

  def getUpdateVersoin: Int = {
    updateVersoin
  }

  def setUpdateVersoin(updateVersoin: Int) {
    this.updateVersoin = updateVersoin
  }

  def getLoopType: Int = {
    loopType
  }

  def setLoopType(loopType: Int) {
    this.loopType = loopType
  }
}