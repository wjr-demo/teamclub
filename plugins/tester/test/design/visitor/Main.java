package design.visitor;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-8-8.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        WeatherData wd = new WeatherData();
        wd.registerObserver(new ForecastDisplay());
        wd.setTemperature("37.3度");
        TimeUnit.SECONDS.sleep(1);
        wd.setTemperature("37.4度");
    }
}
