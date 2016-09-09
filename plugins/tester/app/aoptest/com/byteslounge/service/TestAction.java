package aoptest.com.byteslounge.service;

import aoptest.AroundTest;
import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import play.Logger;
import play.libs.F;
import play.libs.WS;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.atomic.AtomicInteger;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-7-14.
 */
@Service("testAction")
public class TestAction extends play.mvc.Controller{

    public Result xnotify(){
        Logger.info("NOTIFY IT..");
        return Results.badRequest();
    }
}
