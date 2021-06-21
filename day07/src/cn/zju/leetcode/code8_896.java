package cn.zju.leetcode;

public class code8_896 {
    public boolean isMonotonic(int[] A) {
        int n = A.length;
        if (n == 1) {
            return true;
        }
        int i = 1;
        while (i < n && A[i - 1] == A[i]) {
            i++;
        }
        boolean b = false;
        if (i < n) {
            b = A[i - 1] < A[i];
        }
        while (i < n) {
            while (i < n && A[i - 1] == A[i]) {
                i++;
            }
            if (i < n && ((A[i - 1] < A[i]) != b)) {
                return false;
            }
            i++;
        }
        return true;
    }
    public boolean isMonotonic1(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                inc = false;
            }
            if (A[i] < A[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }
}
