package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class code17_1717 {
    public int maximumGain(String s, int x, int y) {
        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        int ret = 0;
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
            s = new StringBuffer(s).reverse().toString();
        }
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c != 'a') {
                stack1.addLast(c);
            } else {
                if (!stack1.isEmpty() && stack1.peekLast() == 'b') {
                    stack1.pollLast();
                    ret += y;
                } else {
                    stack1.addLast(c);
                }
            }
        }
        while (!stack1.isEmpty()) {
            char c = stack1.pollLast();
            if (c != 'a') {
                stack2.addLast(c);
            } else {
                if (!stack2.isEmpty() && stack2.peekLast() == 'b') {
                    stack2.pollLast();
                    ret += x;
                } else {
                    stack2.addLast(c);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
    }
}
