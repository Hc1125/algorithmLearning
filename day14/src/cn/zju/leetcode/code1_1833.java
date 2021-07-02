package cn.zju.leetcode;

public class code1_1833 {
    public static int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100001];
        // 计数排序
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                coins -= curCount * i;
            } else {
                break;
            }
        }
        return count;
    }

}
