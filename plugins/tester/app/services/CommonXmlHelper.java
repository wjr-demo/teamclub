package services;

import org.springframework.stereotype.Service;

/**
 * Created by zhangmeng on 16-7-18.
 */
@Service("commonXmlHelper")
public class CommonXmlHelper implements IXmlHelper {
    @Override
    public void name() {
        System.out.println("Common Xml Helper..");
    }
}
