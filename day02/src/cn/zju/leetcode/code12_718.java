package cn.zju.leetcode;

public class code12_718 {
    private static int findLength(int[] A, int[] B){
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m+1][n+1];
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = 0;
                }
                ans = Math.max(ans,dp[i][j]);
            }

        }
        return ans;
    }
    private static int findLength1(int[] A, int[] B){
        int m = A.length, n = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m,n-i);
            int maxlen = maxLength(A,B,i,0,len);
            ret = Math.max(ret,maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n,m-i);
            int maxlen = maxLength(A,B,0,i,len);
            ret = Math.max(ret,maxlen);
        }
        return ret;
    }
    private static int maxLength(int[] A, int[] B, int addA, int addB, int len){
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if(A[addA+i] == B[addB+i]){
                k++;
            }else{
                k = 0;
            }
            ret = Math.max(ret,k);
        }
        return ret;
    }
    private static int findLength2(int[] A, int[] B){
        int m = A.length, n = B.length;
        int ret = 0;
        for(int diff = -(m-1);diff <= n-1;diff++){
            for(int i = Math.max(0,-diff),l = 0; i<Math.min(m,n-diff);i++){
                l = A[i] == B[i+diff] ? (l+1) : 0;
                ret = Math.max(ret,l);
            }
        }
        return ret;
    }
}
