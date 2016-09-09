package services;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangmeng on 16-7-18.
 */
public abstract class BaseCashService implements IcashService{

    protected IXmlHelper xmlHelper = xmlHelper();

    @Autowired
    private OtherBean otherBean;

    @Override
    public void show() {
        otherBean.show();
        xmlHelper.name();
    }
}
