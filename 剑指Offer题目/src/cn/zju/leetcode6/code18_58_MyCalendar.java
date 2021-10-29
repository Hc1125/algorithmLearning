package cn.zju.leetcode6;


import java.util.TreeMap;

public class code18_58_MyCalendar {
    class MyCalendar {
        TreeMap<Integer, Integer> map;
        public MyCalendar() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prev = map.floorKey(start);
            Integer next = map.ceilingKey(start);
            if ((prev == null || map.get(prev) <= start) && (next == null || end <= next)) {
                map.put(start, end);
                return true;
            }
            return false;
        }
    }
}
