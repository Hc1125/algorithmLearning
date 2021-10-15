package cn.zju.leetcode4;

import java.util.Arrays;

public class code14_CheckInclusion_ThreeSolutions {
    public boolean checkInclusion1(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = m; i < n; i++) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - m) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[s1.charAt(i) - 'a']--;
            cnt[s2.charAt(i) - 'a']++;
        }
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                diff++;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = m; i < n; i++) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - m) - 'a';
            if (x == y) {
                continue;
            }
            if (cnt[x] == 0) {
                diff++;
            }
            cnt[x]++;
            if (cnt[x] == 0) {
                diff--;
            }
            if (cnt[y] == 0) {
                diff++;
            }
            cnt[y]--;
            if (cnt[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean checkInclusion3(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int i = 0; i < n; i++) {
            int x = s2.charAt(i) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                cnt[s2.charAt(left++) - 'a']--;
            }
            if (i - left + 1 == m) {
                return true;
            }
        }
        return false;
    }
}
