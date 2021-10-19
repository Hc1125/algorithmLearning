package cn.zju.leetcode5;

import java.util.ArrayDeque;
import java.util.Deque;

public class code17_37 {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        circle : for (int num : asteroids) {
            if (num > 0) {
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0) {
                    if (-num > stack.peek()) {
                        stack.pop();
                    } else if (-num == stack.peek()) {
                        stack.pop();
                        continue circle;
                    } else {
                        continue circle;
                    }
                }
                stack.push(num);
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
