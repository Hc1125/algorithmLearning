package cn.zju.leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class code11_30 {
    class MinStack {
        Deque<Integer> A, B;
        public MinStack() {
            A = new ArrayDeque<>();
            B = new ArrayDeque<>();
        }

        public void push(int x) {
            A.addLast(x);
            if (B.isEmpty() || B.peekLast() >= x) {
                B.addLast(x);
            }
        }

        public void pop() {
            if (A.pollLast().equals(B.peekLast()))
                B.pollLast();
        }

        public int top() {
            return A.peekLast();
        }

        public int min() {
            return B.peekLast();
        }
    }
}
