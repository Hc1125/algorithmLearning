package cn.zju.leetcode;

import java.util.LinkedList;

public class code13_654 {
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        int n = nums.length;
        return process(nums, 0, n - 1);
    }
    public TreeNode process(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new TreeNode(nums[begin]);
        }
        int max = begin;
        for (int i = begin + 1; i <= end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        TreeNode root = new TreeNode(nums[max]);
        root.left = process(nums, begin, max - 1);
        root.right = process(nums, max + 1, end);
        return root;
    }
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        int n = nums.length;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(new TreeNode(nums[0]));
        for (int i = 1; i < n; i++) {
            if (nums[i] < stack.peekLast().val) {
                stack.addLast(new TreeNode(nums[i]));
            } else {
                TreeNode curNode = new TreeNode(nums[i]);
                while (!stack.isEmpty() && stack.peekLast().val < nums[i]) {
                    TreeNode node = stack.pollLast();
                    if (stack.isEmpty() || stack.peekLast().val > nums[i]) {
                        curNode.left = node;
                    } else {
                        stack.peekLast().right = node;
                    }
                }
                stack.addLast(curNode);
            }
        }
        while (stack.size() > 1) {
            TreeNode node = stack.pollLast();
            stack.peekLast().right = node;
        }
        return stack.peekLast();
    }

}
