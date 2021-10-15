package cn.zju.leetcode4;

public class code17_MinWindow {
    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return "";
        }
        int[] cnt = new int[58];
        for (int i = 0; i < n; i++) {
            cnt[t.charAt(i) - 'A']--;
        }
        int l = 0;
        int ans = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        for (int i = 0; i < m; i++) {
            cnt[s.charAt(i) - 'A']++;
            while (check(cnt)) {
                if (i - l < ans) {
                    ans = i - l;
                    ansL = l;
                    ansR = i + 1;
                }
                cnt[s.charAt(l++) - 'A']--;
            }
        }
        return ans == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR);
    }

    public boolean check(int[] cnt) {
        for (int i : cnt) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }


}
