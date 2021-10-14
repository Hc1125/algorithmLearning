package week4_2021_10_10;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * 给你一个长度为 2 * n 的整数数组。你需要将 nums 分成两个长度为 n 的数组，分别求出两个数组的和，并最小化两个数组和之差的绝对值 。
 * nums 中每个元素都需要放入两个数组之一。
 *
 * 请你返回 最小 的数组和之差。
 */
public class code2_5897 {
    public static int minimumDifference(int[] arr) {
        int size = arr.length;
        int half = size >> 1;
        HashMap<Integer, TreeSet<Integer>> lmap = new HashMap<>();
        process(arr, 0, half, 0, 0, lmap);
        HashMap<Integer, TreeSet<Integer>> rmap = new HashMap<>();
        process(arr, half, size, 0, 0, rmap);
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int ans = Integer.MAX_VALUE;
        for (int leftNum : lmap.keySet()) {
            for (int leftSum : lmap.get(leftNum)) {
                Integer rightSum = rmap.get(half - leftNum).floor(sum - leftSum);
                if (rightSum != null) {
                    int pickSum = leftSum + rightSum;
                    int restSum = sum - pickSum;
                    ans = Math.min(ans, restSum - pickSum);
                    if (ans == 0) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    public static void process(int[] arr, int index, int end, int pick, int sum,
                               HashMap<Integer, TreeSet<Integer>> map) {
        if (index == end) {
            if (!map.containsKey(pick)) {
                map.put(pick, new TreeSet<>());
            }
            map.get(pick).add(sum);
        } else {
            process(arr, index + 1, end, pick, sum, map);
            process(arr, index + 1, end, pick + 1, sum + arr[index], map);
        }
    }
}
