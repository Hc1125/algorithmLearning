package cn.zju.leetcode;

public class code12_1004 {
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }
        int ans = 0;
        for (int right = 0; right < n; right++) {
            int left = binarySearch(P, P[right + 1] - K);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    public int longestOnes1(int[] A, int K) {
        int n = A.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; right++) {
            rsum += 1 - A[right];
            while (lsum < rsum - K) {
                lsum += 1 - A[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
