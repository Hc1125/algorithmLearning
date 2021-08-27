package cn.zju.leetcode;


public class code4_543 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    int ans;
    public int diameterOfBinaryTree1(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }
    public int diameterOfBinaryTree2(TreeNode root) {
        return process(root).diam - 1;
    }
    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int length = Math.max(leftInfo.length, rightInfo.length) + 1;
        int diam = Math.max(leftInfo.diam, rightInfo.diam);
        diam = Math.max(diam, leftInfo.length + 1 + rightInfo.length);
        return new Info(length, diam);
    }
    public class Info {
        public int length;
        public int diam;

        public Info(int length, int diam) {
            this.length = length;
            this.diam = diam;
        }
    }
}
