package cn.zju.leetcode2;

public class code5_55_I {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    public int process(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        return Math.max(process(root.left, depth + 1), process(root.right, depth + 1));
    }
}
