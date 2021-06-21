package cn.zju.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code19_205 {
    public static void main(String[] args) {
        int[] arr = new int[26];
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    private static boolean isIsomorphic1(String s, String t){
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < chars.length; i++) {
            if(preIndexOfs[chars[i]] != preIndexOft[chart[i]]){
                return false;
            }
            preIndexOfs[chars[i]] = i + 1;
            preIndexOft[chart[i]] = i + 1;
        }
        return true;
    }
    private static boolean isIsomorphic2(String s, String t){
        Map<Character,Character> s2t = new HashMap<Character, Character>();
        Map<Character,Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char x = s.charAt(i),y = t.charAt(i);
            if((s2t.containsKey(x) && s2t.get(x) !=y) || (t2s.containsKey(y) && t2s.get(y) !=x)){
                return false;
            }
            s2t.put(x,y);
            t2s.put(y,x);
        }
        return true;
    }
}
