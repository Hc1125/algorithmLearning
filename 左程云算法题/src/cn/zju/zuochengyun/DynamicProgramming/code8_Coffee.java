package cn.zju.zuochengyun.DynamicProgramming;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台洗杯机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡机也可以自己挥发干净，时间耗费b，咖啡机可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr, int N, int a, int b
 */
public class code8_Coffee {
    // 方法二：稍微好一点的解法
    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }
    // 方法二，每个人暴力尝试用每一个咖啡机给自己做咖啡，优化成贪心
    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
            }
        });
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return dp(drinks, a, b);
    }

    /**
     *
     * @param drinks 每一个员工喝完的时间，固定变量
     * @param a      洗一杯的时间 固定变量
     * @param b      自己挥发干净的时间 固定变量
     *    drinks[0..index-1]都已经洗干净了，不用你操心了
     *    drinks[index..]都想变干净，这是我操心的，washLine表示洗的机器何时可用
     *    drinks[index..]变干净，最少的时间点返回
     */
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length) {
            return 0;
        }
        /**
         * 剩余不止一杯咖啡
         * wash是我当前的咖啡杯，洗完的时间
         */
        // index号杯子，决定洗
        int selfClean1 = Math.max(drinks[index], washLine) + a;
        int restClean1 = process(drinks, a, b, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);
        // index号杯子，决定挥发
        int selfClean2 = drinks[index] + b;
        int restClean2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1 ,p2);
    }

    public static int dp(int[] drinks, int a, int b) {
        int N = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < N; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + a;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        // dp[N][...] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free < maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + a;
                if (selfClean1 > maxFree) {
                    continue;
                }
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                int selfClean2 = drinks[index] + b;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
