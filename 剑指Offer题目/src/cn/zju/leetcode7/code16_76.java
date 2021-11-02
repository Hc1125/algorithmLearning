package cn.zju.leetcode7;

//bfprt算法

public class code16_76 {
    public int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, nums.length - k);
    }
    public int process(int[] nums, int l, int r, int target) {
        if (l == r) {
            return nums[l];
        }
        int pivot = medianOfMedians(nums, l, r);
        int[] range = partition(nums, l, r, pivot);
        if (range[1] < target) {
            return process(nums, range[1] + 1, r, target);
        } else if (range[0] > target) {
            return process(nums, l, range[0] - 1, target);
        } else {
            return nums[target];
        }
    }

    public int[] partition(int[] nums, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (nums[cur] < pivot) {
                swap(nums, ++less, cur++);
            } else if (nums[cur] > pivot) {
                swap(nums, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public  int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return process(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
}
