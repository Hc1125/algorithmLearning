package cn.zju.leetcode;

public class code20_330 {
    public static void main(String[] args) {

    }
    private static int minPatches1(int[] nums, int n){
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while(x<=n){
            if(index < length && nums[index] <= x){
                x += nums[index];
                index++;
            }else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
    private static int minPatches2(int[] nums, int n){
        long maxi = 1;
        int i = 0, res = 0;
        while(maxi < n){
            if(i<nums.length && nums[i] <= maxi){
                maxi += nums[i++];
            }else{
                maxi += maxi;
                ++res;
            }
        }
        return res;
    }
}
