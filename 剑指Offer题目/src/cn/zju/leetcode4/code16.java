package cn.zju.leetcode4;

public class code16 {
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[256];
        int l = 0;
        int n = s.length();
        int ans = 0;
        for (int r = 0; r < n; r++) {
            int x = s.charAt(r);
            ++cnt[x];
            while (cnt[x] > 1) {
                cnt[s.charAt(l++)]--;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
