package cn.zju.leetcode;

import java.util.Iterator;

public class code12_284 {
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer nextElement;
        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            nextElement = iterator.next();
        }

        public Integer peek() {
            return nextElement;
        }


        @Override
        public Integer next() {
            Integer ans = nextElement;
            nextElement = iterator.hasNext() ? iterator.next() : null;
            return ans;
        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }
    }
}
