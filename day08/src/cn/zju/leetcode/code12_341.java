package cn.zju.leetcode;

import java.util.*;

public class code12_341 {
    /*public class NestedIterator implements Iterator<Integer> {
        private Deque<Iterator<NestedIterator>> stack;
        public NestedIterator(List<NestedIterator> nestedList) {
            stack = new LinkedList<Iterator<NestedIterator>>();
            stack.push(nestedList.iterator());
        }
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedIterator> it = stack.peek();
                if (!it.hasNext()) {
                    stack.pop();
                    continue;
                }
                NestedIterator nest = it.next();
                if (nest.isInteger()) {
                    List<NestedIterator> list = new ArrayList<NestedIterator>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }

        @Override
        public Integer next() {
            return stack.peek().next().getInteger();
        }
    }*/

}
