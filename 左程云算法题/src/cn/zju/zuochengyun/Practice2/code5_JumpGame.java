package cn.zju.zuochengyun.Practice2;

import java.util.*;
/**
 * 给定一个正数数组arr，你从第0个数向最后一个数
 * 每个数的值表示你从这个位置可以向右跳跃的最大长度
 * 计算如何以最少的跳跃次数跳到最后一个数
 */
public class code5_JumpGame {
    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0;
        int cur = 0;  // step步内能到的最远距离
        int next = 0;
        // step + 1步能到达的最远距离，next需要实时更新
        // 当i>cur，说明要增加step，所以新的cur就等于此时的next
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }


}
