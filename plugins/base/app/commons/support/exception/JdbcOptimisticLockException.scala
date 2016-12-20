package commons.support.exception

@SerialVersionUID(1L)
class JdbcOptimisticLockException(message: String, cause: Throwable) extends RuntimeException(message,cause){
  def this(message: String) = this(message,null)
  def this(cause: Throwable) = this(null,cause)
  def this() = this(null,null)
}