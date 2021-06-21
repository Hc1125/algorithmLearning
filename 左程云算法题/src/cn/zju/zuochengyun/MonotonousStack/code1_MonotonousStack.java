package cn.zju.zuochengyun.MonotonousStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class code1_MonotonousStack {
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int lessLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = lessLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int lessLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = lessLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
                if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                    stack.peek().add(Integer.valueOf(i));
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                }
            }
        }
        return res;
    }
}
