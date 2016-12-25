package libs.backend

/**
  * Created by Administrator on 2016/12/25.
  */
object Permissons extends Enumeration{
  type Permissions = Value
  val EXAMINE = Value    //审核功能
  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}