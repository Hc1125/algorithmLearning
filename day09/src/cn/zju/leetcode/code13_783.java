package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.List;

public class code13_783 {
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
    int pre;
    int ans;
    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        process(root);
        return ans;
    }
    public void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        process(root.right);
    }
}
