package cn.zju.leetcode;


public class code11_1373 {
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
    int ans = Integer.MIN_VALUE;
    public int maxSumBST(TreeNode root) {
        process(root);
        return ans;
    }
    public Info process(TreeNode root) {
        if (root == null) {
            ans = Math.max(ans, 0);
            return new Info(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if ((!leftInfo.isBST) || (!rightInfo.isBST)) {
            return new Info(0, false, root.val, root.val);
        } else {
            if (root.val <= leftInfo.max || root.val >= rightInfo.min) {
                return new Info(0, false, root.val, root.val);
            } else {
                ans = Math.max(root.val + leftInfo.sum + rightInfo.sum, ans);
                return new Info(root.val + leftInfo.sum + rightInfo.sum, true,
                        leftInfo.min == Integer.MAX_VALUE ? root.val : leftInfo.min, rightInfo.max == Integer.MIN_VALUE ? root.val : rightInfo.max);
            }
        }
    }
    public class Info {
        int sum;
        boolean isBST;
        int min;
        int max;

        public Info() {
        }

        public Info(int sum, boolean isBST, int min, int max) {
            this.sum = sum;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }


}
