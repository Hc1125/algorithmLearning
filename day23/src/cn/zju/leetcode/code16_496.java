package cn.zju.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class code16_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peekLast()) {
                map.put(stack.pollLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pollLast(), -1);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
