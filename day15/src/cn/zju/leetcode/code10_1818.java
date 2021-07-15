package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class code10_1818 {
    public int minAbsoluteSumDiff1(int[] nums1, int[] nums2) {
        int base = 1000000007;
        int n = nums1.length;
        int ans = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums1[i]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums1[i] - nums2[i]);
            ans = (abs + ans) % base;
            if (abs > max) {
                Integer floor = set.floor(nums2[i]);
                if (floor != null) {
                    max = Math.max(max, abs - (nums2[i] - floor));
                }
                Integer ceiling = set.ceiling(nums2[i]);
                if (ceiling != null) {
                    max = Math.max(max, abs - (ceiling - nums2[i]));
                }
            }
        }
        return (ans - max + base) % base;
    }

    public int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);
        int sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            if (diff > maxn) {
                int j = binarySearch(rec, nums2[i]);
                if (j < n) {
                    maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
                }
                if (j > 0) {
                    maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
                }
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    public int binarySearch(int[] rec, int target) {
        int low = 0, high = rec.length - 1;
        if (rec[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (rec[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


}
