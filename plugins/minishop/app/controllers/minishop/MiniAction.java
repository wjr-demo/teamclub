package controllers.minishop;

import com.google.common.base.Joiner;
import org.springframework.stereotype.Service;
import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-6-29.
 */
@Service
public class MiniAction extends Controller {
    public Result index(){
        response().setHeader("Content-Security-Policy", "unsafe-url");
        return ok(view("minishop/mini.ftl"));
    }
    public String showHeaders(Map<String, String[]> map ){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for(Map.Entry entry: map.entrySet()){
            sb.append(entry.getKey()).append(Arrays.toString((Object[])entry.getValue())).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    public Result mocktencent(){
        Map<String, String[]> headers = request().headers();
        Logger.info(showHeaders(headers));
        return ok("mock tencent it");
    }
}
