package cn.zju.leetcode;

public class code10_674 {
    public int findLengthOfLCIS(int[] nums){
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int len1 = 0;
        for (int i = 0; i < n - 1; i++) {
            int len2 = 1;
            while(i < n - 1 && nums[i] < nums[i + 1]){
                len2++;
                i++;
            }
            len1 = Math.max(len1,len2);
        }
        return len1;
    }
    public int findLengthOfLCIS1(int[] nums){
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if(i > 0 && nums[i] <= nums[i - 1]){
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}
