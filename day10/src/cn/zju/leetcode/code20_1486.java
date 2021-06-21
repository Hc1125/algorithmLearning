package cn.zju.leetcode;

public class code20_1486 {
    public int xorOperation(int n, int start) {
        int ans = start;
        for (int i = 1; i < n; i++) {
            ans ^= (start + 2 * i);
        }
        return ans;
    }
}
