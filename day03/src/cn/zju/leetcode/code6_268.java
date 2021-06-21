package cn.zju.leetcode;

public class code6_268 {
    public int missingNumber1(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i]>0 && nums[nums[i] -1] != nums[i]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if(nums[i] != i+1){
                return i+1;
            }
        }
        return 0;
    }
    public int missingNumber2(int[] nums){
        long sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            sum -= nums[i-1];
        }
        return (int)sum;
    }
    public int missingNumber3(int[] nums){
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
