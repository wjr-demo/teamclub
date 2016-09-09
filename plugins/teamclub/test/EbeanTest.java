import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import models.teamclub.AppUser;
import models.xteamclub.VersionManager;
import org.junit.Test;

/**
 * Created by zhangmeng on 16-8-30.
 */


public class EbeanTest extends BaseTest{
    @Test
    public void testDB(){
        VersionManager versionManager = new VersionManager();
        versionManager.setCommitVersion("1234");
        versionManager.setFilePath("/asdf/sdf");
        versionManager.save();
    }

    @Test
    public void testEq(){
        Integer i = 12;
        if((i != null) && (i == 10 || i == 12)){
            System.out.println("PingPong");
        }else {
            System.out.println("Error");
        }
    }

}
