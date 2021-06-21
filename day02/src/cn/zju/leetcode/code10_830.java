package cn.zju.leetcode;

import java.util.*;

public class code10_830 {
    private static List<List<Integer>> largerGroupPositions(String s){
        char[] chars = s.toCharArray();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = chars.length;
        int i = 0;
        while(i<n){
            int len = 1;
            while(i<n-1 && chars[i]==chars[i+1]){
                len++;
                i++;
            }
            i++;
            if(len>=3){
                ans.add(Arrays.asList(i-len,i-1));
            }
        }
        return ans;
    }
    private static List<List<Integer>> largerGroupPositions1(String s){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = i + 1;
            while(j<chars.length && chars[j]==chars[i])j++;
            if(j - i >= 3){
                ans.add(Arrays.asList(i,j-1));
            }
            i = j-1;
        }
        return ans;
    }
}
