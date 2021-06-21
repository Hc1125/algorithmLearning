package cn.zju.zuochengyun.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class code10_TwoStackimplementQueue {
    public static class TwoStackQueue {
        public Deque<Integer> stackPush;
        public Deque<Integer> stackPop;

        public TwoStackQueue() {
            stackPush = new ArrayDeque<Integer>();
            stackPop = new ArrayDeque<Integer>();
        }

        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int num) {
            stackPush.push(num);
            pushToPop();
        }

        public int poll(int num) {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
