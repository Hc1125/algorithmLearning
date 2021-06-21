package cn.zju.zuochengyun.Practice1;

/**
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器,请返回容器能装多少水
 * 比如, arr= {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容器可以装下5格水
 * 再比如， arr = {4，5, 1，3，2}，该容器可以装下2格水
 */
public class code10_TrappingRainWater {
    public static int store(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        int maxLeft = arr[0];
        int maxRight = arr[arr.length - 1];
        int i = 1;
        int j = arr.length - 2;
        int ans = 0;
        while (i <= j) {
            if (maxLeft < maxRight) {
                ans += Math.max(0, maxLeft - arr[i]);
                maxLeft = Math.max(arr[i], maxLeft);
                i++;
            } else {
                ans += Math.max(0, maxRight - arr[j]);
                maxRight = Math.max(arr[j], maxRight);
                j--;
            }
        }
        return ans;
    }
}
