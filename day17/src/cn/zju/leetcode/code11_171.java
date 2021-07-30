package cn.zju.leetcode;

public class code11_171 {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans *= 26;
            ans += columnTitle.charAt(i) - 'A' + 1;
        }
        return ans;
    }
}
