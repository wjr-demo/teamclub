package hystrix

import java.util.concurrent.TimeUnit

import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix._
import rx.Observer
import rx.functions.Action1

/**
 * Created by zhangmeng on 17-3-2.
 */
class CommandHelloWorld(name: String) extends HystrixCommand[String](
  Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool"))
    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500))
    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE))
) {

  override def run(): String = {
    TimeUnit.MILLISECONDS.sleep(300)
    s"Hello, $name, thread: ${Thread.currentThread().getName}"
  }

  override def getFallback(): String = {
    "execute Failed!"
  }

}


object TT extends App {
  def split(): Unit ={
    println("--------------------")
  }

  val command = new CommandHelloWorld("test-Fallback")
  val result = command.execute()
  println(result)

}