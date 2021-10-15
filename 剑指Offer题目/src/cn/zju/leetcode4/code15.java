package cn.zju.leetcode4;

import java.util.ArrayList;
import java.util.List;

public class code15 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int m = s.length();
        int n = p.length();
        if (m < n) {
            return ans;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            --cnt[p.charAt(i) - 'a'];
        }
        int l = 0;
        for (int i = 0; i < m; i++) {
            int x = s.charAt(i) - 'a';
            cnt[x]++;
            while (cnt[x] > 0) {
                cnt[s.charAt(l++) - 'a']--;
            }
            if (i - l + 1 == n) {
                ans.add(l);
            }
        }
        return ans;
    }
}
