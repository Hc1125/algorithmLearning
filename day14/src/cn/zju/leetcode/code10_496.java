package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class code10_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums2) {
            while (!stack.isEmpty() && stack.peekLast() < i) {
                map.put(stack.pollLast(), i);
            }
            stack.addLast(i);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
