package cn.zju.leetcode;

public class code20_1540 {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            int dif = chart[i] - chars[i];
            if (dif < 0) {
                dif += 26;
            }
            counts[dif]++;
        }
        for (int i = 1; i < 26; i++) {
            int maxConvert = i + 26 * (counts[i] - 1);
            if (maxConvert > k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int i = 2147483647;
        System.out.println(2 *(long)i);
    }
}
