package cn.zju.leetcode4;

public class code2 {
    public String addBinary(String a, String b) {
        int x = a.length();
        int y = b.length();
        int n = Math.max(x, y);
        int t = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            t += i < x ? (a.charAt(x - 1 - i) - '0') : 0;
            t += i < y ? (b.charAt(y - 1 - i) - '0') : 0;
            ans.append((char) (t % 2 + '0'));
            t /= 2;
        }
        if (t > 0) {
            ans.append('1');
        }
        return ans.reverse().toString();
    }
}
