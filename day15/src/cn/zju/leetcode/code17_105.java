package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code17_105 {
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
    Map<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return process(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    public TreeNode process(int[] preorder, int[] inorder, int begin1, int end1, int begin2, int end2) {
        // 很关键，需要处理空子树！！！
        if (begin1 > end1) {
            return null;
        }
        if (begin1 == end1) {
            return new TreeNode(preorder[begin1]);
        }
        int begin = preorder[begin1];
        TreeNode root = new TreeNode(begin);
        int i = indexMap.get(begin);
        root.left = process(preorder, inorder, begin1 + 1, i - begin2 + begin1, begin2, i - 1);
        root.right = process(preorder, inorder, i - begin2 + begin1 + 1, end1, i + 1, end2);
        return root;
    }
}
