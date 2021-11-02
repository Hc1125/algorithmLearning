package cn.zju.zuochengyun.BinarySearch;

/**
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 */
public class code6_SingleNoRepeat {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        int m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if ((m & 1) == 1) m--;
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
