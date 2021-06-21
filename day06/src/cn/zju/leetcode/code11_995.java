package cn.zju.leetcode;

public class code11_995 {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int left = 0;
        int ans = 0;
        while (left < n) {
            if(A[left] == 0) {
                if(left + K > n) {
                    return -1;
                } else {
                    for (int right = left; right < left + K; right++) {
                        A[right] = 1 - A[right];
                    }
                    ans++;
                }
            }
            left++;
        }
        return ans;
    }
    public int minKBitFlips1(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            revCnt ^= diff[i];
            if(A[i] == revCnt) {
                if(i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                diff[i + K] ^= 1;
            }
        }
        return ans;
    }
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            if(i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2;
            }
            if(A[i] == revCnt) {
                if(i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }
}
