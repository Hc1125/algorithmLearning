package cn.zju.leetcode8;

import java.util.*;

public class code6_86 {
    public String[][] partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
        Deque<String> path = new ArrayDeque<>();
        process(s, 0, dp, path, ans);
        String[][] res = new String[ans.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = new String[ans.get(i).size()];
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = ans.get(i).get(j);
            }
        }
        return res;
    }
    public void process(String s, int index, boolean[][] dp, Deque<String> path, List<List<String>> ans) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]) {
                path.addLast(s.substring(index, i + 1));
                process(s, i + 1, dp, path, ans);
                path.removeLast();
            }
        }
    }
}
