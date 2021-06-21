package cn.zju.leetcode1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code18_32_II {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    List<List<Integer>> res;
    public List<List<Integer>> levelOrder(TreeNode root) {
        res = new ArrayList<>();
        dfs(root, 0);
        return res;
    }
    public void dfs(TreeNode root, int n) {
        if (root == null) {
            return;
        }
        if (res.size() == n) {
            res.add(new ArrayList<>());
        }
        res.get(n).add(root.val);
        dfs(root.left, n + 1);
        dfs(root.right, n + 1);
    }

}
