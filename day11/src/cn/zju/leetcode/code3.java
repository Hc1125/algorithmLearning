package cn.zju.leetcode;

import java.util.*;

public class code3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int[][] matrix = new int[k][3];
            for (int index = 0; index < k; index++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                int w = sc.nextInt();
                int i = x * m + y;
                int j = u * m + v;
                matrix[index][0] = i;
                matrix[index][1] = j;
                matrix[index][2] = w;
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                if (matrix[i][0] == 0) {
                    int dest = matrix[i][1];
                    int result = matrix[i][2];
                    while (dest != n * m - 1) {
                        int p = 0;
                        for (p = 0; p < k; p++) {
                            if (matrix[p][0] == dest) {
                                dest = matrix[p][1];
                                result += matrix[p][2];
                                break;
                            }
                        }
                        if (p == k) {
                            result = Integer.MAX_VALUE;
                            break;
                        }
                    }
                    ans = Math.min(result, ans);
                }
            }
            System.out.println(ans);
        }
    }

}
