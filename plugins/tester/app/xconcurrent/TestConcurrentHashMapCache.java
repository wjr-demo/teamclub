package xconcurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangmeng on 16-10-27.
 */
public class TestConcurrentHashMapCache<K, V> {
    private final ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<K, V>();
}
