package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code3_1306 {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int n = arr.length;
        boolean[] used = new boolean[n];
        queue.add(start);
        used[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (arr[index] == 0) {
                    return true;
                }
                if (isValid(index + arr[index], n) && !used[index + arr[index]]) {
                    queue.add(index + arr[index]);
                    used[index + arr[index]] = true;
                }
                if (isValid(index - arr[index], n) && !used[index - arr[index]]) {
                    queue.add(index - arr[index]);
                    used[index - arr[index]] = true;
                }
            }
        }
        return false;
    }

    public boolean isValid(int index, int n) {
        return index >= 0 && index < n;
    }
}
