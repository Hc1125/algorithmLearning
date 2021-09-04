package part2;

import java.util.Arrays;

// 来自腾讯
// 给定一个正数数组arr，代表每个人的体重。给定一个正数limit代表船的载重，所有船都是同样的载重量
// 每个人的体重都一定不大于船的载重
// 要求：
// 1, 可以1个人单独一搜船
// 2, 一艘船如果坐2人，两个人的体重相加需要是偶数，且总体重不能超过船的载重
// 3, 一艘船最多坐2人
// 返回如果想所有人同时坐船，船的最小数量
public class code7_MinBoatEvenNumbers {

	public static int minBoat(int[] arr, int limit) {
		Arrays.sort(arr);
		int odd = 0;
		int even = 0;
		for (int num : arr) {
			if ((num & 1) == 0) {
				even++;
			} else {
				odd++;
			}
		}
		int[] odds = new int[odd];
		int[] evens = new int[even];
		for (int i = arr.length - 1; i >= 0; i--) {
			if ((arr[i] & 1) == 0) {
				evens[--even] = arr[i];
			} else {
				odds[--odd] = arr[i];
			}
		}
		return min(odds, limit) + min(evens, limit);
	}

	public static int min(int[] arr, int limit) {
		int light = 0, heavy = arr.length - 1;
		int ans = 0;
		while (light <= heavy) {
			if (arr[light] + arr[heavy] <= limit) {
				light++;
			}
			heavy--;
			ans++;
		}
		return ans;
	}
}
