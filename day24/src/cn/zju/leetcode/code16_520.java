package cn.zju.leetcode;

public class code16_520 {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        if (n == 1) {
            return true;
        }
        boolean upper = Character.isUpperCase(word.charAt(1));
        for (int i = 2; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i)) ^ upper) {
                return false;
            }
        }
        return !upper || (upper && Character.isUpperCase(word.charAt(0)));
    }
}
