package strategy;

/**
 * Created by zhangmeng on 16-8-8.
 */
public class TopGetter {
    public Strategy in_strategy;
    public Context context;
    public TopGetter(){
        in_strategy = new Strategy() {
            @Override
            public void doStrategy(Context cont) {
                System.out.println(beDependent());
            }
        };
        context = new Context("Hello World!", in_strategy);
    }
    private String beDependent(){
        return "We will rock you !";
    }
    public void startHere(){
        context.doStrategyNow();
    }
    public static void main(String[] args){
        TopGetter topGetter = new TopGetter();
        topGetter.startHere();
    }
}
