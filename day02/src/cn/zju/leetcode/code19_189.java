package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class code19_189 {
    public void rotate(int[] nums, int k){
        int n = nums.length;
        k = k%n;
        if(k == 0){
            return;
        }
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {
            newArr[i] = nums[n-k+i];
        }
        for (int i = n-1; i > k-1; i--) {
            nums[i] = nums[i-k];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = newArr[i];
        }
    }
    public void rotate1(int[] nums, int k){
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr,0,nums,0,n);
    }
    public void rotate2(int[] nums, int k){
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    public void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public void rotate3(int[] nums, int k){

    }
}
