package cn.zju.zuochengyun.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。给定一个正数aim
 * 每个值都认为是一张货币
 * 认为值相同的货币没有任何不同
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}, aim = 4;
 * 方法： 1 + 1 + 1 + 1, 1 + 1 + 2, 2 + 2
 * 一共就三种方法，所有返回3
 */
public class code11_CoinsWaySameValueSamePaper {
    public static class Info {
        public int[] coins;
        public int[] zhangs;
        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        int N = map.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0 ,aim);
    }

    public static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
            ways += process(coins, zhangs, index + 1, rest - (zhang * coins[index]));
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
                    ways += dp[index + 1][rest - zhang * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

}
