package cn.zju.leetcode2;

public class code8_57 {
    public int[] twoSum(int[] sums, int target) {
        int n = sums.length;
        for (int i = 0; i < n - 1; i++) {
            int res = binarySearch(sums, target - sums[i], i + 1, n - 1);
            if (res != -1) {
                return new int[]{sums[i], sums[res]};
            }
        }
        return new int[]{};
    }
    public static int binarySearch(int[] sums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sums[mid] < target) {
                left = mid + 1;
            } else if (sums[mid] > target){
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public int[] twoSum1(int[] sums, int target) {
        int n = sums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            if (sums[left] + sums[right] > target) {
                right--;
            } else if (sums[left] + sums[right] < target) {
                left++;
            } else {
                return new int[]{sums[left], sums[right]};
            }
        }
        return new int[]{};
    }
}
