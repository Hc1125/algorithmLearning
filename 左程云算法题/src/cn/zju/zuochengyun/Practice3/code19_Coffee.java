package cn.zju.zuochengyun.Practice3;

import java.util.Comparator;
import java.util.PriorityQueue;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要咖啡喝，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class code19_Coffee {
    public static class CoffeeMachine{
        public int start;
        public int work;

        public CoffeeMachine(int s, int w) {
            start = s;
            work = w;
        }
    }

    /**
     * 只是选咖啡机的策略
     * 如果洗杯子不用时间的话，可以这么规划
     */
    public static int[] bestChoices(int[] arr, int M) {
        int[] ans = new int[M];
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new Comparator<CoffeeMachine>() {
            @Override
            public int compare(CoffeeMachine o1, CoffeeMachine o2) {
                return o1.start + o1.work - o2.start - o2.work;
            }
        });
        for(int coffeWork : arr) {
            heap.add(new CoffeeMachine(0, coffeWork));
        }
        for(int i = 0; i < M; i++) {
            CoffeeMachine cur = heap.poll();
            ans[i] = cur.start + cur.work;
            cur.start = ans[i];
            heap.add(cur);
        }
        return ans;
    }
}
