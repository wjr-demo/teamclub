package helper;

/**
 * Created by zhangmeng on 16-11-17.
 */
public class TestEnum {
    enum COLOR {
        RED,
        GREEN
    }
    public static void main(String[] args) {
        System.out.println(COLOR.RED.ordinal());
    }
}
