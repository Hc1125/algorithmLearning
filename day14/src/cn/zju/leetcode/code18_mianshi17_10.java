package cn.zju.leetcode;

public class code18_mianshi17_10 {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int num = 0;
        for (int i : nums) {
            if (num == 0) {
                candidate = i;
                num = 1;
            } else if (candidate == i) {
                num++;
            } else {
                num--;
            }
        }
        num = 0;
        for (int i : nums) {
            if (i == candidate) {
                num++;
            }
        }
        return num > nums.length / 2 ? candidate : -1;
    }
}
