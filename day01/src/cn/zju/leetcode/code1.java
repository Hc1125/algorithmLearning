package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code1 {
    public static void main(String[] args) {
        int[] result = new int[2];
        System.out.println(result[0]);
    }
    private static int[] twoSum1(int[] nums,int target){

        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
    private static int[] twoSum2(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }
}
