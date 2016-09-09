/**
 * Created by zhangmeng on 16-6-27.
 */
import Retry._

import concurrent.{Future, Promise}
import concurrent.future
import scala.util.{Failure, Success}

object WithRetry {
  def main(args: Array[String]): Unit = {
    import concurrent.ExecutionContext.Implicits.global
    val f: Future[String] = future { throw new Exception("haha") }
    val resp = withRetry(3)(f)
    resp.onComplete {
      case Success(s) => println(s)
      case Failure(ex) => println(ex)
    }
  }
}
