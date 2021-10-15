package cn.zju.zuochengyun.Recursion;

import java.util.*;

public class code11_CombinationSum {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len  = candidates.length;
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(candidates, len, 0, target, path, ans);
        return ans;
    }

    public void dfs(int[] arr, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (target - arr[i] < 0) {
                return;
            }
            if (i > begin && arr[i] == arr[i - 1]) {
                continue;
            }
            path.addLast(arr[i]);
            dfs(arr, len, i + 1, target - arr[i], path, ans);
            path.removeLast();
        }
    }
}
