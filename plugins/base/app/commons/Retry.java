
package commons;

import commons.support.exception.JdbcOptimisticLockException;
import play.libs.F.Function0;


public class Retry {
    public static int WAIT_TIME = 300;

    public static <T> T retry(final Function0<T> func){
        return retry(func, 3,WAIT_TIME);
    }

    public static <T> T retry(final Function0<T> func,int count,int waitTime){
        JdbcOptimisticLockException ex = null;
        T rest = null;
        int idx = 0;
        while(count-- > 0){
            try {
                idx ++;
                rest = func.apply();
                count = 0;
                ex = null;
            }catch (JdbcOptimisticLockException e) {
                ex = e;
                try {
                    Thread.sleep(((idx/2)+1) * waitTime);
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            }catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        if(count <= 0 && ex != null){
            throw ex;
        }
        return rest;
    }
}

