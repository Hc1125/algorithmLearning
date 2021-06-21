package cn.zju.leetcode;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class code1_28 {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) {
            return 0;
        }
        char[] str = haystack.toCharArray();
        char[] match = needle.toCharArray();
        int[] next = getNextArray(match);
        int x = 0, y = 0;
        while (x < m && y < n) {
            if (str[x] == str[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == n ? x - y : -1;
    }
    public int[] getNextArray(char[] str) {
        int n = str.length;
        if (n == 1) {
            return new int[] {-1};
        }
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < n) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
