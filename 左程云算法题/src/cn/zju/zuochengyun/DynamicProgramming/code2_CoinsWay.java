package cn.zju.zuochengyun.DynamicProgramming;

/**
 * arr是面值数组，其中的值都是正数且没有重复，给定一个正数aim
 * 每个值都认为是一种面值，且认为张数是无限的
 * 返回组成aim的方法数
 */
public class code2_CoinsWay {
    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /*
        可以自由使用arr[index..]所有的面值，每一种面值都可以使用任意张
        组成rest，有多少种方法
     */
    public static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // 当前有货币，arr[index]
        int ways = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            ways += process1(arr, index + 1, rest - i * arr[index]);
        }
        return ways;
    }
    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++) {
                for (int i = 0; i * arr[index] <= rest; i++) {
                    dp[index][rest] += dp[index + 1][rest - i * arr[index]];
                }
            }
        }
        return dp[0][aim];
    }
    public static int ways3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] > 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }
}
