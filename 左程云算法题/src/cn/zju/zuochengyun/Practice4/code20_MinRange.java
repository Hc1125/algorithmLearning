package cn.zju.zuochengyun.Practice4;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 多个排序数组中取出一个数字，构成一个新数组的范围（最小值到最大值）的最小值
 */
public class code20_MinRange {
    public static int minRange(int[][] matrix) {
        for (int[] arr : matrix) {
            if (arr == null || arr.length == 0) {
                return -1;
            }
        }
        int n = matrix.length;
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < n; i++) {
            set.add(new Node(matrix[i][0], i, 0));
        }
        int min = Integer.MAX_VALUE;
        while (set.size() == n) {
            min = Math.min(min, set.last().value - set.first().value);
            Node node = set.pollFirst();
            int arri = node.arrIndex;
            int owni = node.ownIndex;
            if (owni < matrix[arri].length - 1) {
                set.add(new Node(matrix[arri][owni + 1], arri, owni + 1));
            }
        }
        return min;
    }

    public static class Node {
        public int value;
        public int arrIndex;
        public int ownIndex;

        public Node(int value, int arrIndex, int ownIndex) {
            this.value = value;
            this.arrIndex = arrIndex;
            this.ownIndex = ownIndex;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value == o2.value ? o1.arrIndex - o2.arrIndex : o1.value - o2.value;
        }
    }
}
