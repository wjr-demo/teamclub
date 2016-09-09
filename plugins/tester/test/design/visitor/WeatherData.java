package design.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangmeng on 16-8-8.
 */
public class WeatherData implements ISubject{

    private List<IObserver> observers = Collections.synchronizedList(new ArrayList<IObserver>());

    private String temperature;

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        synchronized (observers) {
            Iterator iter = observers.iterator();
            while(iter.hasNext()) {
                ((IObserver)iter.next()).update(this.temperature);
            }
        }
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
        this.notifyObservers();
    }
}
