package javatest;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import scalatest.xxx.*;
/**
 * Created by zhangmeng on 16-8-18.
 */
public class TTest {

    public static void main(String[] args) {
        BigDecimal count = BigDecimal.ZERO ;
        count = count.add(new BigDecimal(10.0));
        count = count.add(new BigDecimal(20.0));
        System.out.println(count);
    }
}