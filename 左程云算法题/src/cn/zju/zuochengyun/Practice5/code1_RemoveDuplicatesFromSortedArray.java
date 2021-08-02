package cn.zju.zuochengyun.Practice5;

/**
 * 把一个排序完的数组进行去重，直接在原数组上改动，返回改动完的数组对应的有效长度
 */
public class code1_RemoveDuplicatesFromSortedArray {

	public static int removeDuplicates(int[] nums) {
		if (nums == null) {
			return 0;
		}
		if (nums.length < 2) {
			return nums.length;
		}
		int done = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[done]) {
				nums[++done] = nums[i];
			}
		}
		return done + 1;
	}

}
