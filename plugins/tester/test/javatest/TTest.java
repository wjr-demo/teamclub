package javatest;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

public class TTest {
    @Test
    public void BaseTest(){
        Assert.assertEquals(Base64.class.getPackage().getSpecificationVersion(), "1.6");
        System.out.println(new Base64().encodeToString("hello world".getBytes()));
    }
}