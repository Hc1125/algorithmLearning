package cn.zju.leetcode;

import java.util.*;

public class code1_1658 {
    public int minOperations1(int[] nums, int x){
        int n = nums.length;
        Map<Integer, Integer> l_presum = new HashMap<>();
        Map<Integer, Integer> r_presum = new HashMap<>();
        int l_sum = 0;
        for (int i = 0; i < n; i++) {
            l_sum += nums[i];
            if(l_sum > x){
                break;
            }
            l_presum.put(l_sum,i+1);
        }
        int r_sum = 0;
        for(int i = n - 1; i >= 0; i--){
            r_sum += nums[i];
            if(r_sum > x){
                break;
            }
            r_presum.put(r_sum, n - i);
        }
        int l_steps = 0, r_steps = 0;
        l_steps = l_presum.containsKey(x) ? l_presum.get(x) : Integer.MAX_VALUE;
        r_steps = r_presum.containsKey(x) ? r_presum.get(x) : Integer.MAX_VALUE;
        int cur = Math.min(l_steps, r_steps);
        for(int left : l_presum.keySet()){
            if(r_presum.containsKey(x-left)){
                int l = l_presum.get(left);
                int r = r_presum.get(x-left);
                if(l + r <= n){
                    cur = Math.min(cur , l+r);
                }
            }
        }
        return cur != Integer.MAX_VALUE ? cur : -1;
    }
    public int minOperations2(int[] nums, int x){
        int maxPart = -1;
        int sum = Arrays.stream(nums).sum();
        int currentSum = 0;
        int left = 0;
        int right = 0;
        while(left < nums.length){
            if(right < nums.length){
                currentSum += nums[right++];
            }
            while(currentSum > sum - x && left < nums.length){
                currentSum -= nums[left++];
            }
            if(currentSum == sum  - x){
                maxPart = Math.max(maxPart,right - left);
            }
            if(right == nums.length){
                break;
            }
        }
        return maxPart == -1 ? -1 : nums.length - maxPart;
    }
}
