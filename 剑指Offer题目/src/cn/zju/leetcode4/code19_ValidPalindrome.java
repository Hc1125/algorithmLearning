package cn.zju.leetcode4;

public class code19_ValidPalindrome {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        boolean isUsed = false;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                boolean f1 = true, f2 = true;
                for (int i = l, j = r - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        f1 = false;
                        break;
                    }
                }
                for (int i = l + 1, j = r; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        f2 = false;
                        break;
                    }
                }
                return f1 || f2;
            }
        }
        return true;
    }
}
