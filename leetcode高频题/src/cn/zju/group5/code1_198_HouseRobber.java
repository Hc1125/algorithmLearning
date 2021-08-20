package cn.zju.group5;

public class code1_198_HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }
}
