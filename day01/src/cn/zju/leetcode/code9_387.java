package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code9_387 {
    public static void main(String[] args) {
        String s = "abcdefga";
        int index = firstUniqChar(s);
        System.out.println(index);
    }
    private  static int firstUniqChar(String s){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i]-'a',map.getOrDefault(array[i]-'a',0)+1);
        }
        for (int i = 0; i < array.length; i++) {
            if(map.get(array[i]-'a')==1){
                return i;
            }
        }
        return -1;
    }
    //数组比HashMap快很多
    private static int firstUniqChar2(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)-'a']++ ;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i)-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
