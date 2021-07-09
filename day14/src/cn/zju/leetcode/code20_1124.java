package cn.zju.leetcode;

import java.util.LinkedList;

public class code20_1124 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
        }
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(0);
        for (int i = 1; i < n; i++) {
            if (preSum[i] < preSum[stack.peekLast()]) {
                //栈中索引指向的元素严格单调递减
                stack.addLast(i);
            }
        }
        //从后往前遍历 presum 数组
        for (int i = n; i >= 0; i--) {
            //说明栈顶索引到i位置的和是大于0的，是表现良好的时间段
            while (!stack.isEmpty() && preSum[i] > preSum[stack.peekLast()]) {
                //与栈顶索引指向元素比较，如果相减结果大于 0，则一直出栈，直到不大于 0 为止，然后更新当前最大宽度
                ans = Math.max(ans, i - stack.pollLast());
            }
        }
        return ans;
    }
}
