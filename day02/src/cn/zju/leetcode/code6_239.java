package cn.zju.leetcode;

import java.util.*;

public class code6_239 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }

        });
        pq.add(5);
        pq.offer(2);
        pq.offer(3);
        pq.offer(1);
        pq.offer(4);
        while(pq!=null){
            int i = pq.poll();
            System.out.println(i);
        }
    }
    private static int[] maxSlidingWindow1(int[] nums, int k){
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length-k; i++) {
            int[] temp = new int[k];
            for (int j = 0; j < k; j++) {
                temp[j] = nums[i+j];
            }
            ans[i] =  Arrays.stream(temp).max().getAsInt();
        }
        return ans;
    }
    private static int[] maxSlidingWindow2(int[] nums, int k){
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i],i});
        }
        int[] ans = new int[n-k+1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i],i});
            while(pq.peek()[1] <= i - k){
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
    private static int[] maxSlidingWindow3(int[] nums, int k){
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n-k+1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            while(deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
    private static int[] maxSlidingWindow4(int[] nums, int k){
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; i++) {
            if(i % k == 0){
                prefixMax[i] = nums[i];
            }else{
                prefixMax[i] = Math.max(prefixMax[i-1],nums[i]);
            }
        }
        for(int i = n - 1; i >= 0; i--){
            if(i==n-1 || (i+1) % k == 0){
                suffixMax[i] = nums[i];
            }else{
                suffixMax[i] = Math.max(suffixMax[i+1],nums[i]);
            }
        }
        int[] ans = new int[n-k+1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.max(suffixMax[i],prefixMax[i+k-1]);
        }
        return ans;
    }
}
