package cn.zju.leetcode;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class code13_1438 {
    /*
        错误的
     */
    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        if(n == 1) {
            return 1;
        }
        PriorityQueue<int[]> minQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        int left = 0, right = 0;
        int ans = 0;
        while (right < n) {
            minQueue.offer(new int[]{nums[right], right});
            maxQueue.offer(new int[]{nums[right], right});
            int min = minQueue.peek()[0];
            int mini = minQueue.peek()[1];
            int max = maxQueue.peek()[0];
            int maxi = maxQueue.peek()[1];
            if(max - min > limit) {
                ans = Math.max(ans, right - left);
                if(mini < maxi) {
                    while (max - minQueue.peek()[0] > limit) {
                        left = minQueue.peek()[1] + 1;
                        minQueue.poll();
                    }
                } else {
                    while (maxQueue.peek()[0] - min > limit) {
                        left = maxQueue.peek()[1] + 1;
                        maxQueue.poll();
                    }
                }
            }
            right++;
        }
        ans = Math.max(ans, right - left);
        return ans;
    }
    public static int longestSubarray1(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<>();
        Deque<Integer> queMin = new LinkedList<>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }
    public static int longestSubarray2(int[] nums, int limit) {
        int n = nums.length;
        int[] maxq = new int[n], minq = new int[n];
        int hh1 = 0, tt1 = -1, hh2 = 0, tt2 = -1;
        int l = 0, r = 0;
        while (r < n) {
            while (hh1 <= tt1 && nums[maxq[tt1]] < nums[r]) tt1--;
            while (hh2 <= tt2 && nums[minq[tt2]] > nums[r]) tt2--;
            maxq[++tt1] = r;
            minq[++tt2] = r;
            r++;
            if (nums[maxq[hh1]] - nums[minq[hh2]] > limit) {
                l++;
                if (l > maxq[hh1]) hh1++;
                if (l > minq[hh2]) hh2++;
            }
        }
        return r - l;
    }
}
