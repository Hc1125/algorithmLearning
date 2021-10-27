package cn.zju.leetcode6;

public class code7_47 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    public TreeNode pruneTree(TreeNode root) {
        return process(root) ? null : root;
    }
    public boolean process(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean l = process(node.left);
        if (l) node.left = null;
        boolean r = process(node.right);
        if (r) node.right = null;
        return l && r && node.val == 0;
    }
}
