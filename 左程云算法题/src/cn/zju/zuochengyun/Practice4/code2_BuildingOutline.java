package cn.zju.zuochengyun.Practice4;


import java.util.*;

/**
 * 给定一个N×3的矩阵matrix，对于每一个长度为3的小数组arr，都表示一个大楼的三个数据。
 * arr[0]表示大楼的左边界, arr[1]表示大楼的右边界, arr[2]表示大楼的高度(一定大于0)。
 * 每座大楼的地基都在×轴上，大楼之间可能会有重叠,请返回整体的轮廓线数组。
 * 【举例】matrix ={{2,5,6}.{1,7,4}.{4,6,7}.{3,6,5},{10,13,2].{9,11,3},{12,14,4},10,12,5}}
 * 返回: {{1,2.4}.{2,4,6}.{4,6,7}.{6,7.4}.{9,10,3}.{10,12,5},{12,14,4}}
 */
public class code2_BuildingOutline {
    // 描述高度变化的对象
    public static class Op {
        public int x;   // x轴上的值
        public boolean isAdd; // true为加入，false为删除
        public int h;   // 高度


        public Op(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }
    /**
     * 排序的比较策略
     * 1，第一个维度的x值从小到大。
     * 2，如果第一个维度的值相等，看第二个维度的值，“加入”排在前，“删除”排在后
     * 3，如果两个对象第一个维度和第二个维度的值相等，则认为两个对象相等，谁在前都行。
     */
    public static class OpComparator implements Comparator<Op> {
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

    // 全部流程的主方法
    public static List<List<Integer>> buildingOutline(int[][] matrix) {
        Op[] ops = new Op[matrix.length * 2];
        // 每一个大楼轮廓数组，产生两个描述高度变化的对象
        for (int i = 0; i < matrix.length; i++) {
            ops[i * 2] = new Op(matrix[i][0], true, matrix[i][2]);
            ops[i * 2 + 1] = new Op(matrix[i][1], false, matrix[i][2]);
        }

        // 把描述高度变化的对象数组，按照规定的排序策略排序
        Arrays.sort(ops, new OpComparator());
        // TreeMap就是java中的红黑树结构，直接当作有序表来使用
        // key 某个高度 value 次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // key x点 value 最大高度
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].isAdd) {
                // 如果当前是加入操作
                mapHeightTimes.put(ops[i].h, mapHeightTimes.getOrDefault(ops[i].h, 0) + 1);
            } else {
                // 如果当前是删除操作
                if (mapHeightTimes.get(ops[i].h) == 1) {
                    // 如果当前的高度出现过一次，直接删除记录
                    mapHeightTimes.remove(ops[i].h);
                } else {
                    // 如果当前的高度出现次数大于1，次数减1即可
                    mapHeightTimes.put(ops[i].h, mapHeightTimes.get(ops[i].h) - 1);
                }
            }
            // 根据mapHeightTimes中的最大高度，设置mapXvalueHeight表
            if (mapHeightTimes.isEmpty()) {
                // 如果mapHeightTimes为空，说明最大高度为0
                mapXHeight.put(ops[i].x, 0);
            } else {
                // 如果mapHeightTimes不为空，通过mapHeightTimes.lastKey()取得最大高度
                mapXHeight.put(ops[i].x, mapHeightTimes.lastKey());
            }
        }
        // res为结果数组,每一个List<Integer>代表一个轮廓线，有开始位置，结束位置，高度，一共三个信息
        List<List<Integer>> res = new ArrayList<>();
        // 一个新轮廓线的开始位置
        int start = 0;
        // 之前的最大高度
        int preHeight = 0;
        // 根据mapXvalueHeight生成res数组
        for (Map.Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
            // 当前位置
            int curX = entry.getKey();
            // 当前最大高度
            int curMaxHeight = entry.getValue();
            if (preHeight != curMaxHeight) {  // 之前最大高度和当前最大高度不一样时
                if (preHeight != 0) {
                    res.add(new ArrayList<>(Arrays.asList(start, curX, preHeight)));
                }
                start = curX;
                preHeight = curMaxHeight;
            }
        }
        return res;
    }
}
