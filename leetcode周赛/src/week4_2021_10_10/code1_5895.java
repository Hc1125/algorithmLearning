package week4_2021_10_10;

import java.util.Arrays;

public class code1_5895 {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[index++] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        int target = arr[m * n / 2];
        int ans = 0;
        for (int num : arr) {
            if ((num - target) % x != 0) {
                return -1;
            }
            ans += Math.abs((num - target) / x);
        }
        return ans;
    }
}
