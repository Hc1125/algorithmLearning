package cn.zju.zuochengyun.ExclusiveOR;

import java.util.HashMap;
/**
 * 给定一个数组随意划分
 * 在哪一种划分下，让异或和为0的部分最多
 */
public class code4_MostEOR {
    public static int mostEOR(int[] arr) {
        int sum = 0;
        // dp[i] -> arr[0...i]在最优划分的情况下，异或和为0最多的部分是多少个
        int[] dp = new int[arr.length];
        // key : 某个前缀异或和
        // value : 这个前缀异或和出现的最晚的结尾位置（0~value）
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum ^= arr[i];
            /**
             * 假设sum上次出现的结尾是k，0~k的异或和是sum，0~i的异或和也是sum，k+1~i是异或和等于0的
             * 前缀异或和出现的最晚的结尾位置，k+1是离i最近的
             */
            if (map.containsKey(sum)) { // 上一次，这个异或和出现的位置
                // pre -> pre + 1 -> i,最优划分，最后一个部分的开始位置
                // (pre+1, i)最后一个部分
                int pre = map.get(sum);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(sum, i);
        }
        return dp[dp.length - 1];
    }
}
