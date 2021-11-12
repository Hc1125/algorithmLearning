package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code12_488 {
    int INF = 0x3f3f3f3f;
    String b;
    int m;
    Map<String, Integer> map = new HashMap<>();
    public int findMinStep(String board, String hand) {
        b = hand;
        m = b.length();
        int ans = dfs(board, 1 << m);
        return ans == INF ? -1 : ans;
    }

    public int dfs(String a, int cur) {
        if (a.length() == 0) return 0;
        if (map.containsKey(a)) return map.get(a);
        int ans = INF;
        int n = a.length();
        for (int i = 0; i < m; i++) {
            if (((cur >> i) & 1) == 1) continue;
            int next = (1 << i) | cur;
            for (int j = 0; j <= n; j++) {
                if (j > 0 && j < n - 1 && a.charAt(j) == a.charAt(j - 1)) continue;
                if (j > 0 && j < n - 1 && a.charAt(j) != b.charAt(i)) continue;
                StringBuilder tmp = new StringBuilder();
                tmp.append(a.substring(0, j)).append(b.substring(i, i + 1));
                if (j != n) tmp.append(a.substring(j));
                int k = j;
                while (0 <= k && k < tmp.length()) {
                    char c = tmp.charAt(k);
                    int l = k, r = k;
                    while (l >= 0 && tmp.charAt(l) == c) l--;
                    while (r < tmp.length() && tmp.charAt(r) == c) r++;
                    if (r - l - 1 >= 3) {
                        tmp.delete(l + 1, r);
                        k = l >= 0 ? l : r;
                    } else {
                        break;
                    }
                }
                ans = Math.min(ans, dfs(tmp.toString(), next) + 1);
            }
        }
        map.put(a, ans);
        return ans;
    }

    public String eliminate(String s) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0, j = 0; i < s.length(); i = j) {
                while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                    ++j;
                }
                if (j - i >= 3) {
                    s = s.substring(0, i) + s.substring(j);
                    flag = true;
                    break;
                }
            }
        }
        return s;
    }
}
