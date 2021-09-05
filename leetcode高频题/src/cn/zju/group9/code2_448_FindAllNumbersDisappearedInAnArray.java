package cn.zju.group9;

import java.util.ArrayList;
import java.util.List;

public class code2_448_FindAllNumbersDisappearedInAnArray {

	public static List<Integer> findDisappearedNumbers1(int[] nums) {
		int n = nums.length;
		for (int num : nums) {
			int x = (num - 1) % n;
			nums[x] += n;
		}
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= n) {
				ans.add(i + 1);
			}
		}
		return ans;
	}

	public static List<Integer> findDisappearedNumbers2(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		int N = nums.length;
		for (int i = 0; i < N; i++) {
			walk(nums, i);
		}
		for (int i = 0; i < N; i++) {
			if (nums[i] != i + 1) {
				ans.add(i + 1);
			}
		}
		return ans;
	}

	public static void walk(int[] nums, int i) {
		while (nums[i] != i + 1) { // 不断从i发货
			int nexti = nums[i] - 1;
			if (nums[nexti] == nexti + 1) {
				break;
			}
			swap(nums, i, nexti);
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
