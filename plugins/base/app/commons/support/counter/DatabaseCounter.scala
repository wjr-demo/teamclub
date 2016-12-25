package commons.support.counter

import java.sql.{Connection, PreparedStatement, ResultSet, SQLException}

import com.avaje.ebean.{SqlRow, Ebean}
import commons.Retry
import commons.support.exception.JdbcOptimisticLockException
import play.Logger
import play.db.DB
import play.libs.F


class DatabaseCounter extends CounterStandard[CounterEntity] {
  object Libs {
    def currentSeconds: Int = {
      return (System.currentTimeMillis / 1000).toInt
    }
  }
  private val LOGGER: Logger.ALogger = Logger.of(classOf[DatabaseCounter])
  private var tableName: String = "weixin_counter"
  private var insertSql: String = "insert into %s(id,hash_code,counter,update_at,create_at,loop_type,counter_type) values(?,?,?,?,?,?,?)"
  private var updateSql: String = "update %s set counter=?,update_at=?,update_versoin=? where hash_code=? and id=? and update_versoin=?"
  private var selectSql: String = "select id,counter,update_at,create_at,loop_type,counter_type,update_versoin from %s where hash_code=:hashCode and id=:id"
  private var deleteSql: String = "delete from %s where hash_code=? and id=?"

  def select(key: String): CounterEntity = {
    val result:SqlRow = Ebean.createSqlQuery(String.format(selectSql, tableName)).setParameter("hashCode", key.hashCode).setParameter("id", key).setMaxRows(1).findUnique()
    if(result ==  null) return null
    var entry: CounterEntity = null
    entry = new CounterEntity
    entry.id = result.getString("id")
    entry.counter = result.getInteger("counter")
    entry.updateAt = result.getInteger("update_at")
    entry.createAt = result.getInteger("create_at")
    entry.loopType = result.getInteger("loop_type")
    entry.counterType = result.getString("counter_type")
    entry.updateVersoin = result.getInteger("update_versoin")
    entry
  }

  def insert(key: String, entry: CounterEntity) {
    val result = Ebean.createSqlUpdate(String.format(insertSql, tableName))
      .setParameter(1, key)
      .setParameter(2, key.hashCode)
      .setParameter(3, entry.counter)
      .setParameter(4, Libs.currentSeconds)
      .setParameter(5, Libs.currentSeconds)
      .setParameter(6, entry.loopType)
      .setParameter(7, entry.counterType)
      .execute()
    if(result != 1) {
      throw new JdbcOptimisticLockException()
    }
  }

  def update(key: String, entry: CounterEntity) {
    val now: Int = Libs.currentSeconds
    val nowCount: Int = CounterUtils.loop(entry.counter, entry.loopType, entry.updateAt)
    val result = Ebean.createSqlUpdate(String.format(updateSql, tableName))
      .setParameter(1, nowCount)
      .setParameter(2, now)
      .setParameter(3, (entry.updateVersoin) + 1)
      .setParameter(4, key.hashCode)
      .setParameter(5, key)
      .setParameter(6, entry.updateVersoin).execute()
    if(result < 1) {
      Logger.warn("update fail")
      throw new JdbcOptimisticLockException()
    }
  }

  def removeCounter(key: String): Boolean = {
    val result = Ebean.createSqlUpdate(String.format(deleteSql, tableName)).setParameter(1, key.hashCode).setParameter(2, key).execute()
    if(result < 1) {
      throw new JdbcOptimisticLockException()
    }
    result < 1
  }

  def create(name: String, counterType: String, initialValue: Int): Boolean = {
    var entry: CounterEntity = select(name)
    if (entry == null) {
      entry = new CounterEntity
      entry.id = name
      entry.counter = initialValue
      entry.counterType = counterType
      entry.createAt = Libs.currentSeconds
      entry.updateAt = Libs.currentSeconds
      insert(name, entry)
    }
    true
  }

  def create(name: String, initialValue: Int): Boolean = {
    create(name, null, initialValue)
  }

  def remove(name: String): Boolean = {
    removeCounter(name)
    true
  }

  def exists(name: String): Boolean = {
    val entry: CounterEntity = select(name)
    entry != null
  }

  def get(name: String): CounterEntity = {
    val entry: CounterEntity = select(name)
    entry
  }

  def set(name: String, value: Int): CounterEntity = {
    Retry.retry(new F.Function0[CounterEntity]() {
      @throws(classOf[Throwable])
      def apply: CounterEntity = {
        val entry: CounterEntity = select(name)
        if (entry != null) {
          entry.counter = value
          update(name, entry)
        }
        entry
      }
    })
  }

  def incr(name: String): CounterEntity = {
    incr(name, 1)
  }

  def incr(name: String, value: Int): CounterEntity = {
    Retry.retry(new F.Function0[CounterEntity]() {
      @throws(classOf[Throwable])
      def apply: CounterEntity = {
        val entry: CounterEntity = select(name)
        if (entry != null) {
          entry.counter += value
          update(name, entry)
        }
        entry
      }
    })
  }

  def decr(name: String): CounterEntity = {
    incr(name, -1)
  }

  def decr(name: String, value: Int): CounterEntity = {
    var vl = value;
    if (value > 0) {
      vl = -value
    }
    incr(name, vl)
  }
}