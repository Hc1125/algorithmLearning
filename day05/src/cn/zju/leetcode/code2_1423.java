package cn.zju.leetcode;

public class code2_1423 {
    public int maxScore(int[] cardPoints, int k){
        int n = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += cardPoints[i];
        }
        if(n == k){
            return sum;
        }
        int min = 0;
        for (int i = 0; i < n - k; i++) {
            min += cardPoints[i];
        }
        int num = min;
        for (int i = n - k; i < n; i++) {
            num = num + cardPoints[i] - cardPoints[i - n + k];
            min = Math.min(min,num);
        }
        return sum - min;
    }
}
