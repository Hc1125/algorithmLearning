package cn.zju.group8;

public class code4_337_HouseRobberIII {
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
    public int rob(TreeNode root) {
        Info info = process(root);
        return Math.max(info.yes, info.no);
    }
    public class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public Info process(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new Info(node.val, 0);
        }
        int yes = node.val;
        int no = 0;
        if (node.left != null) {
            Info leftInfo = process(node.left);
            yes += leftInfo.no;
            no += Math.max(leftInfo.yes, leftInfo.no);
        }
        if (node.right != null) {
            Info rightInfo = process(node.right);
            yes += rightInfo.no;
            no += Math.max(rightInfo.yes, rightInfo.no);
        }
        return new Info(yes, no);
    }
}
