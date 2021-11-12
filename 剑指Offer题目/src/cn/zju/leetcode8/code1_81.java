package cn.zju.leetcode8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code1_81 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        process(candidates, target, 0, path, ans);
        return ans;
    }

    public void process(int[] arr, int target, int index, List<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                return;
            }
            path.add(arr[i]);
            process(arr, target - arr[i], i, path, ans);
            path.remove(path.size() - 1);
        }
    }

}
