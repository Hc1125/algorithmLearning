package cn.zju.leetcode;

import java.util.Arrays;

public class code13_455 {
    private int findContentChildren(int[] g, int[] s){
        Arrays.sort(s);
        Arrays.sort(g);
        int i = 0,j = 0;
        while(i<g.length && j<s.length){
            if(g[i] <= s[j]){
                i++;
            }
            j++;
        }
        return i;
    }

}
