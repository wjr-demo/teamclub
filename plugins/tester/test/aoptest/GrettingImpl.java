package aoptest;

/**
 * Created by zhangmeng on 16-7-13.
 */
public class GrettingImpl implements Gretting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }
}
