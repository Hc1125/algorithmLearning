package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code6_282 {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num.length() == 0) {
            return ans;
        }
        char[] path = new char[num.length() * 2 - 1];
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            dfs(ans, path, i + 1, 0, n, digits, i + 1, target);
            if (n == 0) {
                break;
            }
        }
        return ans;
    }
    public void dfs(List<String> ans, char[] path, int len, long left, long cur, char[] num, int index, int aim) {
        if (index == num.length) {
            if (left + cur == aim) {
                ans.add(new String(path, 0, len));
            }
            return;
        }
        long n = 0;
        int j = len + 1;
        for (int i = index; i < num.length; i++) {
            n = n * 10 + num[i] - '0';
            path[j++] = num[i];
            path[len] = '+';
            dfs(ans, path, j, left + cur, n, num, i + 1, aim);
            path[len] = '-';
            dfs(ans, path, j, left + cur, -n, num, i + 1, aim);
            path[len] = '*';
            dfs(ans, path, j, left, cur * n, num, i + 1, aim);
            if (num[index] == '0') {
                break;
            }
        }
    }
}
