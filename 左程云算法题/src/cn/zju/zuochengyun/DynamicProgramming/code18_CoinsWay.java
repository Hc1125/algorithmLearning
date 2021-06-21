package cn.zju.zuochengyun.DynamicProgramming;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class code18_CoinsWay {
    /**
     * arr是货币数组，其中的值都是正数。再给定一个正数aim。
     * 每个值都认为是一张货币
     * 返回组成aim的最少货币数
     * 注意：
     * 因为是求最少货币数，所以每张货币认为是相同或者不同就不重要了
     */
    public static class Info {
        public int[] coins;
        public int[] zhangs;
        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }
    // dp2时间复杂度：O(arr长度) +O(货币种数 * aim * 每种货币的平均张数)
    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        // 得到info的时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 0; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 这三层for循环，时间复杂度为O（货币种数 * aim * 每种货币的平均张数）
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int zhang = 1; zhang * coins[index] <= aim && zhang <= zhangs[index]; zhang++) {
                    if (rest - zhang * coins[index] >= 0 && dp[index + 1][rest - zhang * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], zhang + dp[index + 1][rest - zhang * coins[index]]);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    // dp3时间复杂度：O(arr长度) + O(货币种数 * aim)
    // 优化需要用到窗口内最小值的更新结构
    public static int dp3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 0; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 虽然是嵌套了很多循环，但是时间复杂度为O(货币种数 * aim)
        // 因为用了窗口内最小值的更新结构
        for (int i = N - 1; i >= 0; i--) {
            for (int mod = 0; mod < Math.min(aim + 1, coins[i]); mod++) {
                LinkedList<Integer> w = new LinkedList<>();
                w.add(mod);
                dp[i][mod] = dp[i + 1][mod];
                for (int r = mod + coins[i]; r <= aim; r += coins[i]) {
                    while (!w.isEmpty() && (dp[i + 1][w.peekLast()] == Integer.MAX_VALUE
                        || dp[i + 1][w.peekLast()] + compensate(w.peekLast(), r, coins[i]) >= dp[i + 1][r])
                    ) {
                        w.pollLast();
                    }
                    w.add(r);
                    int overdue = r - coins[i] * (zhangs[i] + 1);
                    if (w.peekFirst() == overdue) {
                        w.pollLast();
                    }
                    dp[i][r] = dp[i + 1][w.peekFirst()] + compensate(w.peekFirst(), r, coins[i]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }
}
