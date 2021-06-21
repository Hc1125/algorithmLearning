package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class code4_739 {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
    public int[] dailyTemperatures1(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i > 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for(int t = T[i] + 1; t <= 100; ++t){
                if(next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if(warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[T[i]] = i;
        }
        return ans;
    }
}
