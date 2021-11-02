package week7_2021_10_30;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 *
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。
 * 如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。
 * 子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 */
public class code2_5900 {
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] sum = new int[n + 1];
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (s.charAt(i) == '*' ? 1 : 0);
            left[i + 1] = sum[i + 1];
            right[i + 1] = sum[i + 1];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    left[stack.pollFirst() + 1] = sum[i + 1];
                }
            }
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    right[stack.pollFirst() + 1] = sum[i + 1];
                }
            }
        }
        int length = queries.length;
        int[] ans = new int[length];
        int index = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            ans[index++] = Math.max(right[r + 1] - left[l + 1], 0);
        }
        return ans;
    }
}
