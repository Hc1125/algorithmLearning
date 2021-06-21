package cn.zju.leetcode;


public class code1_1723 {
    int[] _jobs;
    int n, _k;
    int ans = 0x3f3f3f3f;
    public int minimumTimeRequired(int[] jobs, int k) {
        _jobs = jobs;
        n = jobs.length;
        _k = k;
        int[] sum = new int[k];
        dfs(0, sum, 0);
        return ans;
    }
    public void dfs(int u, int[] sum, int max) {
        if (max >= ans) return;
        if (u == n) {
            ans = max;
            return;
        }
        for (int i = 0; i < _k; i++) {
            sum[i] += _jobs[u];
            dfs(u + 1, sum, Math.max(max, sum[i]));
            sum[i] -= _jobs[u];
        }
    }
    public int minimumTimeRequired1(int[] jobs, int k) {
        _jobs = jobs;
        n = jobs.length;
        _k = k;
        int[] sum = new int[k];
        dfs1(0, 0, sum, 0);
        return ans;
    }
    public void dfs1(int u, int used, int[] sum, int max) {
        if (max >= ans) return;
        if (u == n) {
            ans = max;
            return;
        }
        if (used < _k) {
            sum[used] = _jobs[u];
            dfs1(u + 1, used + 1, sum, Math.max(sum[used], max));
            sum[used] = 0;
        }
        for (int i = 0; i < used; i++) {
            sum[i] += _jobs[u];
            dfs1(u + 1, used, sum, Math.max(max, sum[i]));
            sum[i] -= _jobs[u];
        }
    }
}
