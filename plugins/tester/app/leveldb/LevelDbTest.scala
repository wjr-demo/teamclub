package leveldb

import java.io.File

import org.iq80.leveldb._
import org.iq80.leveldb.impl.Iq80DBFactory._;
/**
 * Created by zhangmeng on 16-12-22.
 */
object LevelDbTest extends App{
  val options = new Options()
  options.createIfMissing(true)
  val db = factory.open(new File("example"), options)
  try{
    db.put(bytes("Tampa"), bytes("rock"))
    val str = asString(db.get(bytes("Tampa")))
    println(str)
    db.delete(bytes("Tampa"), new WriteOptions())

    val batch = db.createWriteBatch()
    try {
      batch.delete(bytes("Denver"));
      batch.put(bytes("Tampa"), bytes("green"))
      batch.put(bytes("London"), bytes("red"))
      db.write(batch)
    } finally {
      batch.close()
    }

    val iterator = db.iterator()
    try{
      iterator.seekToFirst()
      while(iterator.hasNext) {
        val key = asString(iterator.peekNext().getKey)
        val value = asString(iterator.peekNext().getValue)
        println(s"${key} = ${value}")
        iterator.next()
      }
    } finally {
      iterator.close()
    }

    val ro = new ReadOptions
    ro.snapshot(db.getSnapshot)
    try {
      val snapIterator = db.iterator(ro)
      try{
        snapIterator.seekToFirst()
        while(snapIterator.hasNext) {
          val key = asString(snapIterator.peekNext().getKey)
          val value = asString(snapIterator.peekNext().getValue)
          println(s"${key} = ${value}")
          snapIterator.next()
        }
      } finally {
        snapIterator.close()
      }
    } finally {
      ro.snapshot().close()
    }

    val comparator = new DBComparator {
      override def findShortSuccessor(key: Array[Byte]): Array[Byte] = key

      override def name(): String = "simple"

      override def findShortestSeparator(start: Array[Byte], limit: Array[Byte]): Array[Byte] = start

      override def compare(key1: Array[Byte], key2: Array[Byte]): Int = {
        new String(key1).compareTo(new String(key2))
      }
    }

    val options = new Options()
    options.comparator(comparator)
    options.compressionType(CompressionType.NONE)
    options.cacheSize(100 * 1048576) // 100 MB
    val logger = new Logger {
        override def log(s: String): Unit = println(s)
      }
    options.logger(logger)
    val db1 = factory.open(new File("example1"), options)
    try{
      val sizes = db1.getApproximateSizes(new Range(bytes("a"), bytes("k")), new Range(bytes("k"), bytes("z")))
      println(s"${sizes(0)}, ${sizes(1)}")
      println(s"${db1.getProperty("leveldb.stats")}")
      factory.destroy(new File("example1"), options)
    }finally {
      db1.close()
    }
  } finally {
    db.close()
  }
}
