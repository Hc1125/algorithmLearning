package cn.zju.leetcode2;


import java.util.*;


public class code1_32_III {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) queue.addLast(root);
        boolean isLeft = true;
        while (!queue.isEmpty()) {
            List<Integer> ans = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                if (isLeft) {
                    TreeNode tmp = queue.pollFirst();
                    ans.add(tmp.val);
                    if (tmp.left != null) queue.addLast(tmp.left);
                    if (tmp.right != null) queue.addLast(tmp.right);
                } else {
                    TreeNode tmp = queue.pollLast();
                    ans.add(tmp.val);
                    if (tmp.right != null) queue.addFirst(tmp.right);
                    if (tmp.left != null) queue.addFirst(tmp.left);
                }
            }
            res.add(ans);
            isLeft = !isLeft;
        }
        return res;
    }
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (res.size() % 2 == 1) Collections.reverse(tmp);
            res.add(tmp);
        }
        return res;
    }
}
