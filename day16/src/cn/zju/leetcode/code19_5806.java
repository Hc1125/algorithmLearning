package cn.zju.leetcode;

import java.util.*;

/**
 * 天际线问题改为求和，并且相同值代表不同含义
 */
public class code19_5806 {
    public class Op {
        public int x;
        public boolean isAdd;
        public int h;


        public Op(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public class OpComparator implements Comparator<Op> {
        @Override
        public int compare(Op o1, Op o2) {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }
            if (o1.isAdd != o2.isAdd) {
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        }
    }


    public List<List<Long>> splitPainting(int[][] segments) {
        Op[] ops = new Op[segments.length * 2];
        for (int i = 0; i < segments.length; i++) {
            ops[i * 2] = new Op(segments[i][0], true, segments[i][2]);
            ops[i * 2 + 1] = new Op(segments[i][1], false, segments[i][2]);
        }
        Arrays.sort(ops, new OpComparator());
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        TreeMap<Integer, Long> mapXSum = new TreeMap<>();
        long sum = 0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].isAdd) {
                mapHeightTimes.put(ops[i].h, mapHeightTimes.getOrDefault(ops[i].h, 0) + 1);
                if (mapHeightTimes.get(ops[i].h) == 1) {
                    sum += ops[i].h;
                }
            } else {
                if (mapHeightTimes.get(ops[i].h) == 1) {
                    mapHeightTimes.remove(ops[i].h);
                    sum -= ops[i].h;
                } else {
                    mapHeightTimes.put(ops[i].h, mapHeightTimes.get(ops[i].h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {
                mapXSum.put(ops[i].x, 0L);
            } else {
                /*long sum = 0;
                for (Integer integer : mapHeightTimes.keySet()) {
                    sum += (long) integer;
                }*/
                mapXSum.put(ops[i].x, sum);
            }
        }
        List<List<Long>> res = new ArrayList<>();
        long start = 0;
        long preSum = 0;
        for (Map.Entry<Integer, Long> entry : mapXSum.entrySet()) {
            long curX = (long) entry.getKey();
            long curSum = entry.getValue();
            if (preSum != 0) {
                res.add(new ArrayList<>(Arrays.asList(start, curX, preSum)));
            }
            start = curX;
            preSum = curSum;

        }
        return res;
    }
}
