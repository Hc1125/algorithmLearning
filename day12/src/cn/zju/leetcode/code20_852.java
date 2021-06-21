package cn.zju.leetcode;

public class code20_852 {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 2;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
