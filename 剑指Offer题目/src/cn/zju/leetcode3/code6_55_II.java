package cn.zju.leetcode3;

import java.io.ObjectStreamException;

public class code6_55_II {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isBalanced(TreeNode root) {
        return process(root) != -1;

    }
    public int process(TreeNode root) {
        if (root == null) return 0;
        int left = process(root.left);
        if (left == -1) return -1;
        int right = process(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
