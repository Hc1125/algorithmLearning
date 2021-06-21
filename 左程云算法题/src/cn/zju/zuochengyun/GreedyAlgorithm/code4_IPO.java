package cn.zju.zuochengyun.GreedyAlgorithm;

import java.util.PriorityQueue;

public class code4_IPO {
    /**
     * 最多K个项目
     * W是初始资金
     * Profits[]
     * Profits[] Captial[] 一定等长
     * 返回最终最大的资金
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>((o1, o2) -> (o1.c - o2.c));
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>((o1, o2) -> (o2.p - o1.p));
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
    public static class Program {
        public int p;
        public int c;
        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
}
