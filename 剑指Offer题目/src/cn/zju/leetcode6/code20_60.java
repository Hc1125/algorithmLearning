package cn.zju.leetcode6;

import java.util.HashMap;
import java.util.Map;

public class code20_60 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] arr = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index++] = entry.getValue();
        }
        int pivot = process(arr, 0, arr.length - 1, arr.length - k);
        int[] ans = new int[k];
        index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= pivot) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }

    public int process(int[] arr, int l, int r, int target) {
        if (l == r) {
            return arr[l];
        }
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = partition(arr, l, r, pivot);
        if (range[0] > target) {
            return process(arr, l, range[0] - 1, target);
        } else if (range[1] < target) {
            return process(arr, range[1] + 1, r, target);
        } else {
            return arr[target];
        }
    }

    public int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
