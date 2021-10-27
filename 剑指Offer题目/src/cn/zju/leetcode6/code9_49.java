package cn.zju.leetcode6;

public class code9_49 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    public int sumNumbers(TreeNode root) {
        return process(root, 0);
    }
    public int process(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        sum = sum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        } else {
            return process(node.left, sum) + process(node.right, sum);
        }
    }
}
