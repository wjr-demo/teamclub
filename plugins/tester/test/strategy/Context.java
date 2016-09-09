package strategy;

/**
 * Created by zhangmeng on 16-8-8.
 */
public class Context {
    private String baseTxt;
    private Strategy strategy;

    public Context(String baseTxt, Strategy strategy) {
        this.baseTxt = baseTxt;
        this.strategy = strategy;
    }

    public void doStrategyNow() {
        System.out.println(baseTxt);
        strategy.doStrategy(this);
    }
}
