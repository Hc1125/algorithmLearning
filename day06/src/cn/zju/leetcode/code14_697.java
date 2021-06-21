package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code14_697 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }
    public int findShortestSubArray1(int[] nums) {
        int n = nums.length;
        int[] visited = new int[50000];
        int maxFreq = 0;
        for (int num : nums) {
            visited[num]++;
            maxFreq = Math.max(maxFreq, visited[num]);
        }
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0;
        visited = new int[50000];
        while (right < n) {
            visited[nums[right]]++;
            right++;
            while (visited[nums[right - 1]] == maxFreq) {
                res = Math.min(res, right - left);
                visited[nums[left]]--;
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
