/**
 * Created by zhangmeng on 16-6-27.
 */
import scala.concurrent._
import play.api.libs.concurrent.Execution.Implicits._
object Retry {
  def withRetry[T](retries: Int = 0)(f: => Future[T]): Future[T] = {
    f recoverWith {
      case t: Throwable if(retries > 0) => {
          println(s"retry ${retries - 1} times")
          withRetry(retries - 1)(f)
      }
    }
  }
}
