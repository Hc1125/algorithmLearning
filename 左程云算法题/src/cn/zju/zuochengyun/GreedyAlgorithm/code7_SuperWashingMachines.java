package cn.zju.zuochengyun.GreedyAlgorithm;

import java.util.Arrays;

// 本题测试链接 : https://leetcode.com/problems/super-washing-machines/
public class code7_SuperWashingMachines {

	public int findMinMoves1(int[] machines) {
		int sum = Arrays.stream(machines).sum();
		int size = machines.length;
		if (sum % size != 0) {
			return -1;
		}
		int avg = sum / size;
		int leftSum = 0;
		int ans = 0;
		for (int num : machines) {
			num -= avg;
			leftSum += num;
			ans = Math.max(ans, Math.max(Math.abs(leftSum), num));
		}
		return ans;
	}

	public static int findMinMoves2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int size = arr.length;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += arr[i];
		}
		if (sum % size != 0) {
			return -1;
		}
		int avg = sum / size;
		int leftSum = 0;
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			int leftRest = leftSum - i * avg;
			int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
			if (leftRest < 0 && rightRest < 0) {
				ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
			} else {
				ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
			}
			leftSum += arr[i];
		}
		return ans;
	}

}
