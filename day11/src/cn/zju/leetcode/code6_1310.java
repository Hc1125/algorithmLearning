package cn.zju.leetcode;

import java.util.concurrent.ThreadPoolExecutor;

public class code6_1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        int length = arr.length;
        int[] pre = new int[length];
        pre[0] = arr[0];
        for (int i = 1; i < length; i++) {
            pre[i] = pre[i - 1] ^ arr[i];
        }
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l = queries[i][0];
            r = queries[i][1];
            ans[i] ^= pre[r];
            if (l > 0) {
                ans[i] ^= pre[l - 1];
            }
        }
        return ans;
    }

}
