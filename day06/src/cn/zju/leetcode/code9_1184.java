package cn.zju.leetcode;

import java.util.Arrays;

public class code9_1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int sum = Arrays.stream(distance).sum();
        int n = distance.length;
        int ans = 0;
        if(start < destination) {
            for (int i = start; i < destination; i++) {
                ans += distance[i];
            }
        } else {
            for (int i = start; i < n; i++) {
                ans += distance[i];
            }
            for (int i = 0; i < destination; i++) {
                ans += distance[i];
            }
        }
        return ans < sum - ans ? ans : sum - ans;
    }
    public int distanceBetweenBusStops1(int[] distance, int start, int destination) {
        int sum = Arrays.stream(distance).sum();
        int n = distance.length;
        int ans = 0;
        if(destination < start) {
            int temp = destination;
            destination = start;
            start = temp;
        }
        for (int i = start; i < destination; i++) {
            ans += distance[i];
        }
        return ans < sum - ans ? ans : sum - ans;
    }
}
