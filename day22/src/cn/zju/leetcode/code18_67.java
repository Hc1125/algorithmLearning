package cn.zju.leetcode;

public class code18_67 {
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        StringBuilder ans = new StringBuilder();
        int t = 0;
        for (int i = 0; i < m || i < n; i++) {
            t += i < m ? a.charAt(m - 1 - i) - '0' : 0;
            t += i < n ? b.charAt(n - 1 - i) - '0' : 0;
            ans.append(t & 1);
            t >>= 1;
        }
        if (t != 0) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }

}
