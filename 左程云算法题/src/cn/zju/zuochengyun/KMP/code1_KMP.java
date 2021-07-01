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
        /**
         * O(N)
         * 复杂度证明
         * xMax -> n , yMax -> m -> n
         * 循环中三个判断条件  X(max, n)  X - Y(max, n)
         *       (1)            上升       不变
         *       (2)            上升       上升
         *       (3)            不变       上升
         *       X和 X - Y在所有的判断条件中只有上升或者不变
         *       且变化幅度最大都是n
         *       所以两者变化幅度合起来不超过2n
         *       ->    O(N)
         */

        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (y > 0) {
                y = next[y];
            } else {
                x++;
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
