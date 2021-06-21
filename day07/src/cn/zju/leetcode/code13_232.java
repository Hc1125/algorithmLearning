package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class code13_232 {
    class MyQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
