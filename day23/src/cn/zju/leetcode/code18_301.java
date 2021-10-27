package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code18_301 {
    public List<String> removeInvalidParentheses(String s) {
        int lremove = 0;
        int rremove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lremove++;
            } else if (s.charAt(i) == ')') {
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        process(s, 0, 0, 0, lremove, rremove, ans);
        return ans;
    }

    public void process(String str, int start, int lcount, int rcount, int lremove, int rremove, List<String> ans) {
        if (lremove == 0 && rremove == 0) {
            if (isValid(str)) {
                ans.add(str);
            }
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                if (str.charAt(i) == '(') {
                    lcount++;
                } else if (str.charAt(i) == ')') {
                    rcount++;
                }
                continue;
            }
            if (lremove + rremove > str.length() - i) {
                return;
            }
            if (lremove > 0 && str.charAt(i) == '(') {
                process(str.substring(0, i) + str.substring(i + 1), i, lcount, rcount, lremove - 1, rremove, ans);
            }
            if (rremove > 0 && str.charAt(i) == ')') {
                process(str.substring(0, i) + str.substring(i + 1), i, lcount, rcount, lremove, rremove - 1, ans);
            }
            if (str.charAt(i) == '(') {
                lcount++;
            } else if (str.charAt(i) == ')') {
                rcount++;
            }
            if (rcount > lcount) {
                return;
            }
        }
    }
    public boolean isValid(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }
}
