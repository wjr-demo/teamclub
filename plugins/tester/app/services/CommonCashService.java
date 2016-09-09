package services;

/**
 * Created by zhangmeng on 16-7-18.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import plugins.spring.Spring;

@Service("cashsyncservice.common")
public class CommonCashService extends BaseCashService{
    @Override
    public IXmlHelper xmlHelper() {
        return Spring.getBeanOfType(GuilinXmlHelper.class);
    }
    @Autowired
    @Qualifier("commonXmlHelper")
    protected IXmlHelper xmlHelper;
}
