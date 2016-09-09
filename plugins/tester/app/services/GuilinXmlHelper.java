package services;

import org.springframework.stereotype.Service;

/**
 * Created by zhangmeng on 16-7-18.
 */
@Service
public class GuilinXmlHelper implements IXmlHelper {
    @Override
    public void name() {
        System.out.println("GuilinXmlHelper");
    }
}
