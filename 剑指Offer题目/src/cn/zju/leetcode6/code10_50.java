package cn.zju.leetcode6;

import java.util.HashMap;
import java.util.Map;

public class code10_50 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return process(root, 0, preSum, targetSum);
    }
    public int process(TreeNode root, int sum, Map<Integer, Integer> preSum, int target) {
        if (root == null) {
            return 0;
        }
        sum += root.val;
        int ans = 0;
        ans += preSum.getOrDefault(sum - target, 0);
        preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        ans += process(root.left, sum, preSum, target);
        ans += process(root.right, sum, preSum, target);
        preSum.put(sum, preSum.get(sum) - 1);
        return ans;
    }
}
