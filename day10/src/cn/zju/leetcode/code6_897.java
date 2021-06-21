package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class code6_897 {

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
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        TreeNode head = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
        list.get(list.size() - 1).left = null;
        list.get(list.size() - 1).right = null;
        return head;
    }
    public void process(TreeNode root, List<TreeNode> list) {
        if (root.left != null) {
            process(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            process(root.right, list);
        }

    }
    private TreeNode cur;
    public TreeNode increasingBST1(TreeNode root) {
        TreeNode head = new TreeNode(-1);
        cur = head;
        inorder(root);
        return head.right;
    }
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        cur.right = root;
        root.left = null;
        cur = cur.right;
        inorder(root.right);
    }
}
