package cn.zju.zuochengyun.Practice3;

/**
 * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
 * 作为一个事后诸葛亮，你想知道如果随便交易且每次交易只买卖一股，返回能挣到的最大盈利
 */
public class code3_BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }
        return ans;
    }
}
