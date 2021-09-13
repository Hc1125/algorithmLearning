package cn.zju.leetcode;


import java.util.ArrayDeque;
import java.util.Deque;

public class code19_678 {
    public boolean checkValidString1(String s) {
        int min = 0, max = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                min = Math.max(min - 1, 0);
                max--;
                if (max < 0) {
                    return false;
                }
            } else {
                max++;
                min = Math.max(min - 1, 0);
            }
        }
        return min == 0;
    }

    public boolean checkValidString2(String s) {
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> starStack = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int starIndex = starStack.pop();
            if (leftIndex > starIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }
}
