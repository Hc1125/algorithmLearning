package cn.zju.leetcode7;

import java.util.ArrayList;
import java.util.List;

public class code20_80 {
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(1, n, k, path, ans);
        return ans;
    }

    public void dfs(int index, int n, int k, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (path.size() + n - index + 1 < k) {
            return;
        }
        dfs(index + 1, n, k, path, ans);
        path.add(index);
        dfs(index + 1, n, k, path, ans);
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            path.add(i);
        }
        path.add(n + 1);
        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<>(path.subList(0, k)));
            j = 0;
            while (j < k && path.get(j) + 1 == path.get(j + 1)) {
                path.set(j, j + 1);
                ++j;
            }
            path.set(j, path.get(j) + 1);
        }
        return ans;
    }
}
