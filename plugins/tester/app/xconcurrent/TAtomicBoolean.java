package xconcurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangmeng on 16-10-28.
 */
public class TAtomicBoolean {
    public static void main(String[] args) {
        AtomicBoolean abool = new AtomicBoolean(true);
        abool.compareAndSet(true, false);
        System.out.println(abool.get());
        System.out.println(abool.getAndSet(true));

    }
}
