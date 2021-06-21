package cn.zju.leetcode1;

public class code1_20 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        try {
            double a = Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }
        char c = s.charAt(s.length() - 1);
        return (c >= '0' && c <= '9') || c == '.';
    }
}
