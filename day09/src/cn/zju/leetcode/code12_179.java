package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class code12_179 {
    public String largestNumber(int[] nums) {
        StringBuffer sb = new StringBuffer();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int)(merge(o2, o1) - merge(o1, o2));
            }
        });
        for (int num : nums) {
            pq.offer(num);
        }
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                break;
            }
        }
        if (i == nums.length) {
            return "0";
        }
        while (!pq.isEmpty()) {
            sb.append(String.valueOf(pq.poll()));
        }
        return sb.toString();
    }
    public static long merge(int num1, int num2) {
        if (num1 == 0) {
            return num2;
        }
        if (num2 == 0) {
            return num1 * 10;
        }
        int index = 0;
        int temp = num2;
        while (num2 > 0) {
            num2 /= 10;
            index++;
        }
        return (long)(num1 * Math.pow(10, index) + temp);
    }
    public String largestNumber1(int[] nums) {
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x ,y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (sx * y + x - sy * x - y);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuffer ret = new StringBuffer();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }
}
