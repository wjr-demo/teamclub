package javatest;

import scalatest.InvokeJavaArray;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by zhangmeng on 16-8-23.
 */
public class ObjectArrayT {
    public static void show(Object[] objs) {
        for(Object obj: objs) {
            System.out.println(obj.getClass().getName());
        }
    }
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add("hello");
        list.add("world");
        InvokeJavaArray.getAny(new Object[] {"hello", "world"});
    }
}
