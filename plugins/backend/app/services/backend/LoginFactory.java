package services.backend;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import play.Play;
import plugins.spring.Spring;

/**
 * Created by zhangmeng on 16-9-7.
 */
@Component("backend.loginFactory")
public class LoginFactory implements FactoryBean<ILogin>{
    String loginService = Play.application().configuration().getString("loginService");
    @Override
    public Class<?> getObjectType() {
        return ILogin.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public ILogin getObject() throws Exception {
        return (ILogin)Spring.getBean(loginService);
    }
}
