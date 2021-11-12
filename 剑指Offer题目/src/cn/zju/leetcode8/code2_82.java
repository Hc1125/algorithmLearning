package cn.zju.leetcode8;

import java.util.*;

public class code2_82 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        process(candidates, target, 0, path, ans);
        return ans;
    }
    public void process(int[] arr, int target, int index, Deque<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (target < 0 || index == arr.length) {
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (target - arr[i] < 0) {
                return;
            }
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            path.addLast(arr[i]);
            process(arr, target - arr[i], i + 1, path, ans);
            path.removeLast();
        }
    }
}
