package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zhangmeng on 16-10-27.
 */
public class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED ;
    }
    private static Unsafe getUnsafe() {
        try {

            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Guard guard = new Guard();
        System.out.println(guard.giveAccess());

        Unsafe unsafe = getUnsafe();

        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");

        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);

        System.out.println(guard.giveAccess());

    }
}
