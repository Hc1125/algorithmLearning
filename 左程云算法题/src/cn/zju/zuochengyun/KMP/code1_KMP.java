package cn.zju.zuochengyun.KMP;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class code1_KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0, y = 0;
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : - 1;
    }
    
    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (match[i - 1] == match[cn]) {
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
