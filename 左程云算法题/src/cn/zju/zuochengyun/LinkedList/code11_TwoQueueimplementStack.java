package cn.zju.zuochengyun.LinkedList;

import java.util.LinkedList;
import java.util.Queue;

public class code11_TwoQueueimplementStack {
    public class TwoQueueStack {
        Queue<Integer> queue;
        Queue<Integer> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(int num) {
            queue.offer(num);
        }

        public int pop() {
            if (queue.isEmpty() && help.isEmpty()) {
                throw new RuntimeException("Stack is Empty!");
            }
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public int peek() {
            if (queue.isEmpty() && help.isEmpty()) {
                throw new RuntimeException("Stack is Empty!");
            }
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            help.offer(ans);
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }
}
