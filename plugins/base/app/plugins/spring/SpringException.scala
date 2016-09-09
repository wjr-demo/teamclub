package plugins.spring

/**
 * Created by zhangmeng on 16-6-23.
 */
import play.api.UnexpectedException

/**
 *
 * @author wsargent
 * @since 12/10/11
 */

//class SpringException(title: String = "Spring context is not started!",
//                     description: String = "The Spring application context is not started.",
//                      cause: Option[Throwable] = None) extends PlayException(title, description, cause) {

class SpringException( description: Option[String] = Option("The Spring application context is not started."),
                       cause: Option[Throwable] = None) extends UnexpectedException(description, cause) {
}