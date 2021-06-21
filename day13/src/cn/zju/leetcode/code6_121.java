package cn.zju.leetcode;

public class code6_121 {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int ans = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            ans = Math.max(price - min, ans);
        }
        return ans;
    }
}
