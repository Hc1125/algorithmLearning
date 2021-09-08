package cn.zju.leetcode;

public class code14_1221 {
    public int balancedStringSplit(String s) {
        int i = 0;
        int j = 0;
        int ans = 0;
        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(k) == 'L') {
                i++;
            } else {
                j++;
            }
            if (i == j) {
                ans++;
            }
        }
        return ans;
    }
}
