package cn.zju.zuochengyun.IndexTree;


import java.util.Arrays;

/**
 * 给定一个数组arr，arr[i] = j，表示第i号试题的难度为j。给定一个非负数M
 * 想出一张卷子，对于任何相邻的两道题目，前一题的难度不能超过后一题的难度+M
 * 返回所有可能的卷子种数
 */
public class code3_SequenceM {
    public int ways(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        code1_IndexTree.IndexTree itree = new code1_IndexTree.IndexTree(max - min + 2);
        Arrays.sort(arr);
        int a = 0;
        int b = 0;
        int all = 1;
        itree.add(arr[0] - min + 1, 1);
        for (int i = 1; i < arr.length; i++) {
            a = arr[i] - min + 1;
            // b 为在已经加入的题目中大于等于arr[i] - m的数目
            b = i - (a - m - 1 >= 1 ? itree.sum(a - m - 1) : 0);
            all = all * (b + 1);
            itree.add(a, 1);
        }
        return all;
    }
}
