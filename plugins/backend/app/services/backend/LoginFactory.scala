package services.backend

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.FactoryBean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import play.Play
import plugins.spring.Spring

/**
 * Created by zhangmeng on 16-9-7.
 */
@Component("backend.loginFactory")
class LoginFactory extends FactoryBean[ILogin] {
  private[backend] var loginService: String = Play.application.configuration.getString("loginService")

  def getObjectType: Class[_] = {
    return classOf[ILogin]
  }

  def isSingleton: Boolean = {
    return true
  }

  @throws(classOf[Exception])
  def getObject: ILogin = {
    if (loginService == null) {
      loginService = "backend.LoginService"
    }
    return Spring.getBean(loginService).asInstanceOf[ILogin]
  }
}