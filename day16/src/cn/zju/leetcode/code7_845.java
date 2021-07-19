package cn.zju.leetcode;

public class code7_845 {
    public int longestMountain(int[] arr) {
        int i = 1, n = arr.length;
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        while (i < n) {
            while (i < n && arr[i - 1] >= arr[i]) {
                i++;
            }
            int begin = i - 1;
            while (i < n && arr[i - 1] < arr[i]) {
                i++;
            }
            if (i == n || arr[i - 1] == arr[i]) {
                continue;
            }
            while (i < n && arr[i - 1] > arr[i]) {
                i++;
            }
            ans = Math.max(ans, i - begin);
        }
        return ans;
    }
}
