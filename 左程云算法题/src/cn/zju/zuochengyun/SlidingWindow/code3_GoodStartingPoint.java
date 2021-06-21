package cn.zju.zuochengyun.SlidingWindow;

import java.util.LinkedList;

public class code3_GoodStartingPoint {
    // 加油站的良好出发点问题
    // 给定一个distance数组和gas数组
    // distance数组为该点到下一点距离
    // gas数组为该点的油数
    // 假定汽车油箱无限大，选定哪些起点能跑完一圈回到原点
    // 例子distance[2,2,1,1] gas[1,1,2,2]
    // 返回可行答案数组[false,false,true,false]
    // 将上述数组转换成[-1,-1,1,1]
    // 四个点的过程则为[-1,-2,-1,0] ,[-1,0,1,0] ,[1,2,1,0] ,[1,0,-1,0]有出现小于0的点即是false
    // 生成一个2N-1长度前缀累加和[-1,-2,-1,0,-1,-2,-1]
    public static boolean[] goodStart(int[] distance, int[] gas) {
        int N = distance.length;
        int[] rest = new int[N];
        for (int i = 0; i < N; i++) {
            rest[i] = gas[i] - distance[i];
        }
        int[] window = new int[2 * N - 1];
        window[0] = rest[0];
        for (int i = 1; i < window.length; i++) {
            window[i] += rest[i % N] + window[i - 1];
        }
        boolean[] ans = new boolean[N];
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        int index = 0;
        while (R < 2 * N - 1) {
            while (!minWindow.isEmpty() && window[minWindow.peekLast()] >= window[R]) {
                minWindow.pollLast();
            }
            minWindow.addLast(R);
            if (R - N + 1 >= 0) {
                ans[index] = index > 0 ? window[minWindow.peekFirst()] - window[index - 1] >= 0 : window[minWindow.peekFirst()] >= 0;
                index++;
            }
            while (minWindow.peekFirst() < index) {
                minWindow.pollFirst();
            }
            R++;
        }
        return ans;
    }

}
