package cn.zju.zuochengyun.Practice3;

/**
 * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
 * 作为一个事后诸葛亮，你想知道如果交易次数不超过k次，且每次交易只买卖一股，返回能挣到的最大盈利
 */
public class code4_BestTimeToBuyAndSellStock3 {
    public static int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        // 状态压缩
        int[] dp = new int[N];
        for (int i = 1; i <= K; i++) {
            int pre = dp[0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[index];
                dp[index] = Math.max(dp[index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);

            }
        }
        return dp[N - 1];
    }

    public static int dp(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        // dp[i][j] 代表用了0~i个股票价格做交易，交易次数不超过j次能获得的最大利润
        /**
         * 第一种情况，第i次的股票价格不参与交易：dp[i - 1][j]
         * 第二种情况，第i次的股票价格参与交易：枚举买入股票的时机,卖出的价格则一定是prices[i]
         * dp[i][j - 1] - prices[i] + prices[i]
         * dp[i - 1][j - 1] - prices[i - 1] + prices[i]
         * ......
         * dp[1][j -1] - prices[1] + prices[i]
         * dp[0][j -1] - prices[0] + prices[i]
         * 其中取最大值
         * 前面部分可以复用 ,抽象为 best = dp[i][j - 1] - prices[i]
         * 最后加上prices[i]即为答案
         */
        int[][] dp = new int[N][K + 1];
        // dp[0][...] = 0;
        // dp[...][0] = 0;
        for (int j = 1; j <= K; j++) {
            int best = dp[0][j - 1] - prices[0];
            for (int i = 1; i < N; i++) {
                best = Math.max(best, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(dp[i - 1][j], best + prices[i]);
            }
        }
        return dp[N - 1][K];
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }
        return ans;
    }
}
