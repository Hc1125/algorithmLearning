package cn.zju.leetcode;

public class code9_1004 {
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int num = 0;
        int left = 0, right = 0;
        int ans = 0;
        while (right < n) {
            num += A[right] == 0 ? 1 : 0;
            while (num > K) {
                num -= A[left] == 0 ? 1 : 0;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
