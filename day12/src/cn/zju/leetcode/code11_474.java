package cn.zju.leetcode;

public class code11_474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            int[] zeroOnes = getZerosOnes(strs[i]);
            int zeros = zeroOnes[0], ones = zeroOnes[1];
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
    public int[] getZerosOnes(String str) {
        int[] zeroOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zeroOnes[str.charAt(i) - '0']++;
        }
        return zeroOnes;
    }
}
