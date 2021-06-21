package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code18_992 {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0, right = 0;
        int count = 0;
        int res = 0;
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;
            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }
    public int subarraysWithKDistinct1(int[] A, int K) {
        int n = A.length;
        int[] num1 = new int[n + 1];
        int[] num2 = new int[n + 1];
        int tot1 = 0, tot2 = 0;
        int left1 = 0, left2 = 0, right = 0;
        int ret = 0;
        while (right < n) {
            if(num1[A[right]] == 0) {
                tot1++;
            }
            num1[A[right]]++;
            if(num2[A[right]] == 0) {
                tot2++;
            }
            num2[A[right]]++;
            while (tot1 > K) {
                num1[A[left1]]--;
                if(num1[A[left1]] == 0) {
                    tot1--;
                }
                left1++;
            }
            while (tot2 > K - 1) {
                num2[A[left2]]--;
                if(num2[A[left2]] == 0) {
                    tot2--;
                }
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }
}
