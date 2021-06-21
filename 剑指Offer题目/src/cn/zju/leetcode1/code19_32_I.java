package cn.zju.leetcode1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code19_32_I {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int[] levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                ans.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }
}
