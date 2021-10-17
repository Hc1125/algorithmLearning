package week5_2021_10_16;

/**
 * 给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，
 * 请你返回第 k （从 1 开始编号）小的 nums1[i] * nums2[j] 的乘积，
 * 其中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
 * 1 <= nums1.length, nums2.length <= 5 * 104
 * -105 <= nums1[i], nums2[j] <= 105
 * 1 <= k <= nums1.length * nums2.length
 * nums1 和 nums2 都是从小到大排好序的。
 */
public class code2_5887 {
    /**
     * 通过二分查找第 k 小的乘积 p，每次判定时枚举 nums1 中的数 a，通过二分再次判断 nums2 中有几个数 b 满足 ab <= p。
     * 注意需要对 a > 0,a < 0和 a = 0 三种情况分别讨论。
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long head = (long)-1e11, tail = (long)1e11;
        while (head < tail) {
            long mid = (head + tail) >> 1;
            long x = count(mid, nums1, nums2);
            if (x >= k) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        return head;
    }

    public long count(long lim, int[] A, int[] B) {
        long ans = 0;
        for (long x : A) {
            int head = 0, tail = B.length - 1;
            if (x > 0) {
                if (x * B[0] > lim) continue;
                while (head < tail) {
                    int mid = (head + tail + 1) >> 1;
                    if (x * B[mid] <= lim) head = mid;
                    else tail = mid - 1;
                }
                ans += head + 1;
            } else if (x < 0) {
                if (x * B[B.length - 1] > lim) continue;
                while (head < tail) {
                    int mid = (head + tail) >> 1;
                    if (x * B[mid] <= lim) tail = mid;
                    else head = mid + 1;
                }
                ans += B.length - head;
            } else if (lim >= 0) ans += B.length;
        }
        return ans;
    }
}
