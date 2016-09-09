package javatest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangmeng on 16-7-26.
 */
public class TestList {
    public static void main(String[] args){
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        for(int i = 0 ; i < 10000; i++){
            list1.add("test" + i);
            list2.add("test" + i * 2);
        }
        getDifferent(list1, list2);
        getDifferent2(list1, list2);
        getDifferent3(list1, list2);
    }

    private static List<String> getDifferent(List<String> list1, List<String> list2){
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for(String str: list1){
            if(!list2.contains(str)){
                diff.add(str);
            }
        }
        System.out.println("getDifferent total times " + (System.nanoTime() - st));
        return diff;
    }

    private static List<String> getDifferent2(List<String> list1, List<String> list2){
        long st = System.nanoTime();
        list1.retainAll(list2);
        System.out.println("getDifferent2 total times " + (System.nanoTime() - st));
        return list1;
    }

    private static List<String> getDifferent3(List<String> list1, List<String> list2){
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        for(String string: list1){
            map.put(string, 1);
        }
        for(String string: list2) {
            Integer cc = map.get(string);
            if(cc != null){
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDifferent3 total times " + (System.nanoTime() - st));
        return list1;
    }

    private static List<String> getDifferent4(List<String> list1, List<String> list2){
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        return null;
    }
}
