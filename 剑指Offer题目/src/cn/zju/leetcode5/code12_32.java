package cn.zju.leetcode5;

public class code12_32 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = t.charAt(i) - 'a';
            if (--cnt[c] < 0) {
                return false;
            }
        }
        return !s.equals(t);
    }
}
