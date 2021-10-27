package cn.zju.leetcode6;

public class code11_51_MaxPathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    int ans = Integer.MIN_VALUE;
    public int maxPathSum1(TreeNode root) {
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        int pathSum = root.val + left + right;
        ans = Math.max(ans, pathSum);
        return root.val + Math.max(left, right);
    }


    public class Info {
        int sumFromHead;
        int maxSum;

        public Info(int sumFromHead, int maxSum) {
            this.sumFromHead = sumFromHead;
            this.maxSum = maxSum;
        }
    }
    public int maxPathSum2(TreeNode root) {
        return process(root).maxSum;
    }
    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int sumFromHead = root.val;
        if (left != null) {
            sumFromHead = Math.max(sumFromHead, left.sumFromHead + root.val);
        }
        if (right != null) {
            sumFromHead = Math.max(sumFromHead, right.sumFromHead + root.val);
        }
        int maxSum = sumFromHead;
        if (left != null) {
            maxSum = Math.max(maxSum, left.maxSum);
        }
        if (right != null) {
            maxSum = Math.max(maxSum, right.maxSum);
        }
        if (left != null && right != null && left.sumFromHead > 0 && right.sumFromHead > 0) {
            maxSum = Math.max(maxSum, left.sumFromHead + right.sumFromHead + root.val);
        }
        return new Info(sumFromHead, maxSum);
    }
}
