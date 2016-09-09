package leveldb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import static org.iq80.leveldb.impl.Iq80DBFactory.*;
import org.junit.Test;

import java.io.File;

/**
 * Created by zhangmeng on 16-8-4.
 */
public class LevelDBTest {
    @Test
    public void initDB() throws Exception{
        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File("example"), options);
        try{
            db.put(bytes("Tampa"), new byte[] {0x23});
            System.out.println( db.get(bytes("Tampa")) == null);
        }finally {
            db.close();
        }
    }
}
