package cn.zju.leetcode;

public class code11_1035 {
    private static int maxUncrossedLines1(int[] A, int[] B){
        int m = A.length;
        int n = B.length;
        int ans = 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(ans,dp[i][j]);
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return ans;
    }
}
