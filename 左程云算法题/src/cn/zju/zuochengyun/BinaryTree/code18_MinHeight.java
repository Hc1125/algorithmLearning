package cn.zju.zuochengyun.BinaryTree;


public class code18_MinHeight {
    public static int minHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return p(head);
    }
    public static int p(TreeNode x) {
        if (x.left == null && x.right == null) {
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if (x.left != null) {
            leftH = p(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (x.right != null) {
            rightH = p(x.right);
        }
        return 1 + Math.min(leftH, rightH);
    }
}
