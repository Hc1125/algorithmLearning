package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class code14_mianshi08_09 {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        process(0, 0,0, new StringBuilder(), n);
        return ans;
    }
    public void process(int i, int j, int sum, StringBuilder sb, int n) {
        if (i == n && j == n) {
            ans.add(sb.toString());
            return;
        }
        if (sum < 0) {
            return;
        }
        if (i < n) {
            process(i + 1, j, sum + 1, sb.append("("), n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (j < n) {
            process(i, j + 1, sum - 1, sb.append(")"), n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
