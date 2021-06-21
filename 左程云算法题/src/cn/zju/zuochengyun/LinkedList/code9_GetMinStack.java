package cn.zju.zuochengyun.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class code9_GetMinStack {
    public class getMinStack {
        Deque<Integer> pushStack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();
        private void push(int num) {
            pushStack.push(num);
            if (minStack.isEmpty() || num <= minStack.peek()) {
                minStack.push(num);
            } else {
                minStack.push(minStack.peek());
            }
        }

        private int pop() {
            minStack.pop();
            return pushStack.pop();
        }

        private int getMin() {
            return minStack.peek();
        }
    }
}
