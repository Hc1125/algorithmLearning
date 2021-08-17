package cn.zju.leetcode;

public class code11_551 {
    public boolean checkRecord(String s) {
        int a = 0;
        int l = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'A') {
                l = 0;
                if (++a > 1) {
                    return false;
                }
            } else if (s.charAt(i) == 'L') {
                if (++l > 2) {
                    return false;
                }
            } else {
                l = 0;
            }
        }
        return true;
    }
}
