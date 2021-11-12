package cn.zju.leetcode8;

import java.util.ArrayList;
import java.util.List;

public class code5_85 {
    public List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(path, 0, 0, n, ans);
        return ans;
    }
    public void process(char[] path, int index, int leftMinusRight, int left, List<String> ans) {
        if (index == path.length) {
            ans.add(new String(path));
            return;
        }
        if (left > 0) {
            path[index] = '(';
            process(path, index + 1, leftMinusRight + 1, left - 1, ans);
        }
        if (leftMinusRight > 0) {
            path[index] = ')';
            process(path, index + 1, leftMinusRight - 1, left, ans);
        }
    }

}
