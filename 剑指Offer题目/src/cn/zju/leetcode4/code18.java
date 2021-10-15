package cn.zju.leetcode4;

public class code18 {
    public boolean isPalindrome(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(s.charAt(l)) && !Character.isDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetter(s.charAt(r)) && !Character.isDigit(s.charAt(r))) {
                r--;
            }
            if (Character.isLetter(s.charAt(l))) {
                if (Character.toUpperCase(s.charAt(l++)) != Character.toUpperCase(s.charAt(r--))) {
                    return false;
                }
            } else {
                if (s.charAt(l++) != s.charAt(r--)) {
                    return false;
                }
            }
        }
        return true;
    }
}
