package cn.zju.leetcode;

import java.util.HashMap;


public class code1_437 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return process(root, sum, 0, preSumMap);
    }
    public int process(TreeNode node, int sum, int preAll, HashMap<Integer, Integer> preSumMap) {
        if (node == null) {
            return 0;
        }
        int all = preAll + node.val;
        int ans = 0;
        if (preSumMap.containsKey(all - sum)) {
            ans = preSumMap.get(all - sum);
        }
        preSumMap.put(all, preSumMap.getOrDefault(all, 0) + 1);
        ans += process(node.left, sum, all, preSumMap);
        ans += process(node.right, sum, all, preSumMap);
        if (preSumMap.get(all) == 1) {
            preSumMap.remove(all);
        } else {
            preSumMap.put(all, preSumMap.get(all) - 1);
        }
        return ans;
    }
}
