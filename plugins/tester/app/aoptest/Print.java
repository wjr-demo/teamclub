package aoptest;

import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import org.springframework.stereotype.Component;
import play.Logger;

/**
 * Created by zhangmeng on 16-12-12.
 */
@Component
public class Print {
    @With({LogTimeInterceptor.class})
    public void print(){
        Logger.error("throw new Exception.");
        throw new RuntimeException("HAHA");
    }
}
