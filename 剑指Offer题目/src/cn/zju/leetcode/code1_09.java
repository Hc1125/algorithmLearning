package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class code1_09 {
    class CQueue {
        Deque<Integer> stack_in;
        Deque<Integer> stack_out;
        public CQueue() {
            stack_in = new LinkedList<Integer>();
            stack_out = new LinkedList<Integer>();
        }
        public void appendTail(int value) {
            stack_in.addLast(value);
        }
        public int deleteHead() {
            if (stack_out.isEmpty()) {
                if (!stack_in.isEmpty()) {
                    while (!stack_in.isEmpty()) {
                        stack_out.addLast(stack_in.pollLast());
                    }
                    return stack_out.pollLast();
                } else {
                    return -1;
                }
            }
            return stack_out.pollLast();
        }
    }
}
