package cn.zju.leetcode2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.IntFunction;

public class code11_46 {
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        return process(str, 0);
    }
    public int process(String str, int i) {
        if (i == str.length()) {
            return 1;
        }
        int num = Integer.valueOf(str.substring(i, i + 1));
        if (num > 2 || num == 0) {
            return process(str, i + 1);
        } else if (num == 1) {
            if (i + 1 < str.length()) {
                return process(str, i + 1) + process(str, i + 2);
            } else {
                return process(str, i + 1);
            }
        } else {
            if (i + 1 < str.length() && Integer.valueOf(str.substring(i, i + 2)) < 26) {
                return process(str, i + 1) + process(str, i + 2);
            } else {
                return process(str, i + 1);
            }
        }
    }
    public int translateNum1(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int p = 0, q = 0 , r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = str.substring(i - 1, i + 1);
            if (pre.compareTo("10") >= 0 && pre.compareTo("25") <= 0) {
                r += p;
            }
        }
        return r;

    }
}
