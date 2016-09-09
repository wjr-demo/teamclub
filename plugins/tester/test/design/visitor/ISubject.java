package design.visitor;

/**
 * Created by zhangmeng on 16-8-8.
 */
public interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
