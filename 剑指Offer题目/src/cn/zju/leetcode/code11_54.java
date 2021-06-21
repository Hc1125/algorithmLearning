package cn.zju.leetcode;


public class code11_54 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        in(root);
        return res;
    }
    public void in(TreeNode root) {
        if (root == null) {
            return;
        }
        in(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        in(root.left);
    }
}
