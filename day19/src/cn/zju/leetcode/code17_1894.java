package cn.zju.leetcode;



public class code17_1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }
        k %= chalk[n - 1];
        int l = 0, r = chalk.length - 1;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (chalk[mid] <= k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
