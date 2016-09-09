package design.visitor;

/**
 * Created by zhangmeng on 16-8-8.
 */
public class ForecastDisplay implements IDisplay, IObserver {
    private String temp;

    @Override
    public void update(String temp) {
        this.temp = temp;
        display();
    }

    @Override
    public void display() {
        System.out.println(String.format("temp is : %s",this.temp));
    }
}
