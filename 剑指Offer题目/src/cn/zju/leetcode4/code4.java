package cn.zju.leetcode4;

public class code4 {
    public int singleNumber(int[] nums) {
        int ans = 0;
        int time = 0;
        for (int i = 0; i < 32; i++) {
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    time++;
                }
            }
            if (time % 3 != 0) {
                ans |= 1 << i;
            }
            time = 0;
        }
        return ans;
    }
}
