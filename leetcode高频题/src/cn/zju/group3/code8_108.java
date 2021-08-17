package cn.zju.group3;

public class code8_108 {
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }
    public TreeNode process(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(nums[i]);
        }
        int m = i + ((j - i) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = process(nums, i, m - 1);
        root.right = process(nums, m + 1, j);
        return root;
    }
}
