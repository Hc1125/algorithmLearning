package cn.zju.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code10_402 {
    public String removeKdigits1(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        if (deque.size() == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (digit != '0' || ans.length() > 0) {
                ans.append(digit);
            } else {
                if (deque.size() == 0) {
                    ans.append('0');
                }
            }
        }
        return ans.toString();
    }
    public String removeKdigits2(String num, int k) {
        int n = num.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = num.charAt(i) - '0';
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int t = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] < stack.peekLast() && t < k) {
                stack.pollLast();
                t++;
            }
            stack.add(arr[i]);
        }
        while (t < k) {
            stack.pollLast();
            t++;
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            int i = stack.pollFirst();
            if (i != 0) {
                ans.append(i);
            } else {
                if (ans.length() > 0) {
                    ans.append(i);
                } else {
                    if (stack.size() == 0) {
                        ans.append(i);
                    }
                }
            }
        }
        return ans.toString();
    }
}
