package cn.zju.leetcode3;

public class code8_63 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1) {
            return 0;
        }
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < n; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
    public int maxProfit1(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - cost);
            cost = Math.min(cost, prices[i]);
        }
        return profit;
    }
}
