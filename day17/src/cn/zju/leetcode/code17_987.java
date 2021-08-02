package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class code17_987 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        Collections.sort(nodes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });
        List<List<Integer>> ans = new ArrayList<>();
        int size = 0;
        int lastCol = Integer.MIN_VALUE;
        for (int[] node : nodes) {
            int row = node[0], col = node[1], val = node[2];
            if (lastCol != col) {
                ans.add(new ArrayList<>());
                size++;
                lastCol = col;
            }
            ans.get(size - 1).add(val);
        }
        return ans;
    }
    public void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(new int[]{row, col, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }

}
