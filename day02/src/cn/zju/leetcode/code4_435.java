package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class code4_435 {
    private static int eraseOverlapIntervals1(int[][] intervals){
        if(intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f,1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(intervals[j][1] <= intervals[i][0]){
                    f[i] = Math.max(f[i],f[j]+1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
    private static int eraseOverlapIntervals2(int[][] intervals){
        if(intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if(intervals[i][0] >= right){
                ans++;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
    private static int eraseOverlapIntervals3(int[][] intervals){
        if(intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < end){
                end = Math.min(end,intervals[i][1]);
                count++;
            }else{
                end = intervals[i][1];
            }
        }
        return count;
    }
}
