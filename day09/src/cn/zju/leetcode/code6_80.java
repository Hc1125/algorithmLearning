package cn.zju.leetcode;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayDeque;
import java.util.Deque;

public class code6_80 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);
        int pre = stack.peek();
        stack.push(nums[1]);
        int cur = stack.peek();
        for (int i = 2; i < n; i++) {
            if (nums[i] == pre && nums[i] == cur) {
                continue;
            } else {
                pre = cur;
                stack.push(nums[i]);
                cur = stack.peek();
            }
        }
        int ans = stack.size();
        for (int i = ans - 1; i >= 0; i--) {
            nums[i] = stack.pop();
        }
        return ans;
    }

    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
