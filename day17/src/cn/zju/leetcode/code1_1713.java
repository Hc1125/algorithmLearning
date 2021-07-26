package cn.zju.leetcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code1_1713 {
    public static int minOperations1(int[] target, int[] arr) {
        int m = target.length;
        int n = arr.length;
        return process(target, arr, m - 1, n - 1);
    }

    public static int process(int[] target, int[] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return target[i] == arr[j] ? 0 : 1;
        }
        if (i == 0) {
            return target[0] == arr[j] ? 0 : process(target, arr, i, j - 1);
        }
        if (j == 0) {
            return target[i] == arr[0] ? i : process(target, arr, i - 1, j) + 1;
        }
        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, process(target, arr, i - 1, j) + 1);
        ans = Math.min(ans, process(target, arr, i, j - 1));
        if (target[i] == arr[j]) {
            ans = Math.min(ans, process(target, arr, i - 1, j - 1));
        }
        return ans;
    }

    public int dp(int[] target, int[] arr) {
        int m = target.length;
        int n = arr.length;
        int[] dp = new int[m];
        dp[0] = target[0] == arr[0] ? 0 : 1;
        for (int i = 1; i < m; i++) {
            dp[i] = target[i] == arr[0] ? i : dp[i - 1] + 1;
        }

        for (int j = 1; j < n; j++) {
            int[] temp = new int[m];
            temp[0] = target[0] == arr[j] ? 0 : dp[0];
            for (int i = 1; i < m; i++) {
                temp[i] = Math.min(dp[i], temp[i - 1] + 1);
                if (target[i] == arr[j]) {
                    temp[i] = Math.min(temp[i], dp[i - 1]);
                }
            }
            dp = temp;
        }
        return dp[m - 1];
    }

    /**
     * 查找数组最长递增子序列的长度
     */
    public int minOperations2(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target[i], i);
        }
        List<Integer> d = new ArrayList<>();
        for (int val : arr) {
            if (map.containsKey(val)) {
                int idx = map.get(val);
                int it = binarySearch(d, idx);
                if (it != d.size()) {
                    d.set(it, idx);
                } else {
                    d.add(idx);
                }
            }
        }
        return n - d.size();
    }

    public int binarySearch(List<Integer> d, int target) {
        int size = d.size();
        if (size == 0 || d.get(size - 1) < target) {
            return size;
        }
        int low = 0, high = size - 1, mid = 0;
        while (low < high) {
            mid = low + ((high - low) >> 1);
            if (d.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
