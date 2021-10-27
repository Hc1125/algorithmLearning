package cn.zju.group16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class code3_446_ArithmeticSlicesIISubsequence {

	// 时间复杂度是O(N^2)，最优解的时间复杂度
	public static int numberOfArithmeticSlices1(int[] arr) {
		int N = arr.length;
		int ans = 0;
		ArrayList<HashMap<Integer, Integer>> maps = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			maps.add(new HashMap<>());
			// 以i结尾，枚举与前面的差值
			for (int j = i - 1; j >= 0; j--) {
				long diff = (long) arr[i] - (long) arr[j];
				if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
					continue;
				}
				int dif = (int) diff;
				int count = maps.get(j).getOrDefault(dif, 0);
				ans += count;
				maps.get(i).put(dif, maps.get(i).getOrDefault(dif, 0) + count + 1);
			}
		}
		return ans;
	}



	public int numberOfArithmeticSlices2(int[] nums) {
		int ans = 0;
		int n = nums.length;
		Map<Long, Integer>[] f = new Map[n];
		for (int i = 0; i < n; ++i) {
			f[i] = new HashMap<Long, Integer>();
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				long d = (long) nums[i] - nums[j];
				int cnt = f[j].getOrDefault(d, 0);
				ans += cnt;
				f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
			}
		}
		return ans;
	}


}
