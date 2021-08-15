package cn.zju.group1;

public class code1_125 {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        while (L < R) {
            if (validChar(str[L]) && validChar(str[R])) {
                if (!equal(str[L], str[R])) {
                    return false;
                }
                L++;
                R--;
            } else {
                L += validChar(str[L]) ? 0 : 1;
                R -= validChar(str[R]) ? 0 : 1;
            }
        }
        return true;
    }

    public boolean validChar(char c) {
        return Character.isDigit(c) || Character.isLetter(c);
    }

    public boolean equal(char c1, char c2) {
        if (Character.isDigit(c1) || Character.isDigit(c2)) {
            return c1 == c2;
        }
        return c1 == c2 || (Math.max(c1, c2) - Math.min(c1, c2) == 32);
    }
}
