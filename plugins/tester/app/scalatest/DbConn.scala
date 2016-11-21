package scalatest

import java.io.{InterruptedIOException, File, FileOutputStream, IOException}
import java.nio.channels.{OverlappingFileLockException, FileLock}
import java.sql.{Connection, DriverManager}
import java.text.SimpleDateFormat
import java.util.{Date, UUID}
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.{TimeUnit, Executors, ExecutorService}

import com.jolbox.bonecp.{BoneCPConfig, BoneCP}
import org.slf4j.LoggerFactory
import play.libs.Akka

/**
 * Created by zhangmeng on 16-11-11.
 */
object DbConn {

  def main(args: Array[String]): Unit = {
    if(new File("./testFile.log").exists()) {
      println(new File("./testFile.log").delete())
//      return
    }
    val logger = LoggerFactory.getLogger("DbConn")
    val driver = "com.mysql.jdbc.Driver"
//    val url = "jdbc:mysql://172.17.20.231:13306/demo"
    val url = "jdbc:mysql://172.17.20.138:3306/test"
    val username = "wxpay"
    val password = "wxpay"

    val all = new AtomicInteger(0)
    val fail = new AtomicInteger(0)

    Class.forName(driver)

    val pool: ExecutorService = Executors.newFixedThreadPool(700)
    val cfg = new BoneCPConfig()
    cfg.setJdbcUrl(url)
    cfg.setUser(username)
    cfg.setPassword(password)
    cfg.setMinConnectionsPerPartition(700)
    cfg.setMaxConnectionsPerPartition(700)
    cfg.setPartitionCount(1)
    var connectionPool:BoneCP  = null
    try {
      connectionPool = new BoneCP(cfg)
      while(true) {
        pool.execute(new Runnable {
          override def run(): Unit = {
            var connection:Connection = null
            try {
              connection = connectionPool.getConnection
              val statement = connection.createStatement()
              val lstT = System.currentTimeMillis()
              val result = statement.executeUpdate(s"insert into emp4(name) values('%s')".format(UUID.randomUUID))
//              val result = statement.executeUpdate(s"insert into t_c(c_one) values('%s')".format(UUID.randomUUID))
              val full = all.get()
              val success = full - fail.get()
              val cost = System.currentTimeMillis() - lstT
              val logStr = "date: %s, Thread name: %s, cost %s , cur rate: %d / %d".format( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date), Thread.currentThread().getName, cost, full, success)

              if(cost > 300) logger.warn(s"$logStr" ) else logger.info(s"$logStr")

            } catch {
              case e: Exception =>  {
                logger.error("", e)
                fail.incrementAndGet()
              }
            }finally {
              if(connection != null) connection.close()
            }
            all.incrementAndGet()
          }
        })
        TimeUnit.MILLISECONDS.sleep(20)
      }
    } catch {
      case e: Exception => logger.error("", e)
    } finally {
      if(connectionPool != null) connectionPool.shutdown()
      pool.shutdown()
    }
  }
}
