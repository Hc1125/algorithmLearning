package cn.zju.zuochengyun.Practice1;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，左部分最大值减去右部分最大值的绝对值
 */
public class code9_MaxABSBetweenleftAndRight {
    public static int maxDif(int[] arr) {
        int max1 = arr[0] < arr[arr.length - 1] ? arr[0] : arr[arr.length - 1];
        int max2 = Integer.MIN_VALUE;
        for (int i : arr) {
            max2 = Math.max(max2, i);
        }
        return max2 - max1;
    }
}
