package aoptest;

/**
 * Created by zhangmeng on 16-7-13.
 */
public class GrettingProxy implements Gretting {
    private Gretting gretting;
    public GrettingProxy(Gretting gretting){
        this.gretting = gretting;
    }

    @Override
    public void sayHello(String name) {
        before();
        gretting.sayHello(name);
        after();
    }

    private void before(){
        System.out.println("Before");
    }
    private void after(){
        System.out.println("After");
    }
}
