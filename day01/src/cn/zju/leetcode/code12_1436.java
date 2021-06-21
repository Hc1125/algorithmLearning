package cn.zju.leetcode;

import java.util.*;

public class code12_1436 {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.remove(1);
        map.remove(2);
        Set<Integer> set = map.keySet();
        System.out.println(set);
    }
    private static String destCity1(List<List<String>> paths){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < paths.size(); i++) {
            List<String> path = paths.get(i);
            for (int j = 0; j < path.size(); j++) {
                String s = path.get(j);
                int index = list.indexOf(s);
                if(index == -1){
                    if(j==1){
                        list.add(s);
                    }else{
                        list.add(0,s);
                    }
                }else{
                    list.remove(index);
                }
            }

        }
        return list.get(1);
    }
    private static String destCity2(List<List<String>> paths){
        HashMap<String,String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0),path.get(1));
        }
        String key = paths.get(0).get(0);
        while(map.containsKey(key)){
            key = map.get(key);
        }
        return key;
    }
}
