package cn.zju.leetcode;



public class code8_938 {
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
    public int sum;
    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        process(root, low, high);
        return sum;
    }
    public void process(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }else if (root.val <= high && root.val >= low) {
            sum += root.val;
            process(root.left, low, high);
            process(root.right, low, high);
        } else if (root.val > high){
            process(root.left, low, high);
        } else {
            process(root.right, low , high);
        }
    }

}
