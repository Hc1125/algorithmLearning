package cn.zju.leetcode;

public class code2_76 {
    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return "";
        }
        int[] cnt = new int[58];
        for (int i = 0; i < n; i++) {
            --cnt[t.charAt(i) - 'A'];
        }
        int l = 0;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        for (int r = 0; r < m; r++) {
            ++cnt[s.charAt(r) - 'A'];
            while (check(cnt)) {
                if (r - l < len) {
                    len = r - l;
                    ansL = l;
                    ansR = r + 1;
                }
                --cnt[s.charAt(l++) - 'A'];
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
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
