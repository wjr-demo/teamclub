package services;

import org.springframework.stereotype.Service;
import play.Logger;
import plugins.spring.Spring;

/**
 * Created by zhangmeng on 16-7-18.
 */
@Service("cashsyncservice.guilin")
public class GuilinCashService implements IcashService {
    @Override
    public IXmlHelper xmlHelper() {
        return Spring.getBeanOfType(GuilinXmlHelper.class);
    }

    @Override
    public void show() {
        xmlHelper().name();
        Logger.info("GuilinCashService show");
    }
}
