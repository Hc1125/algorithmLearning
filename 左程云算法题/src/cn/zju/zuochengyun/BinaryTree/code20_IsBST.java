package cn.zju.zuochengyun.BinaryTree;

public class code20_IsBST {
    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean i, int ma, int mi) {
            isBST = i;
            max = ma;
            min = mi;
        }
    }
    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }


    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= x.val)) {
            isBST = false;
        }
        if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= x.val)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }
}
