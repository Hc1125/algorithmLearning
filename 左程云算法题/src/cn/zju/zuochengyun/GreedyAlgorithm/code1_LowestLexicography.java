package cn.zju.zuochengyun.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class code1_LowestLexicography {
    public static String lowestString1(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs, use, "", all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if(all.get(i).compareTo(lowest) < 0){
                lowest = all.get(i);
            }
        }
        return lowest;
    }
    /*
        strs里放着所有的字符串
        已经使用过的字符串的下标，在use里登记了，不要再使用了
        之前使用过的字符串，拼接成了 -> path
        用all收集所有可能的拼接结果
     */
    public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all){
        if(use.size() == strs.length){
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if(!use.contains(i)) {
                    use.add(i);
                    process(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }
    }
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b){
            return (a + b).compareTo(b + a);
        }
    }
    public static String lowestString2(String[] strs){
        if(strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }
}
