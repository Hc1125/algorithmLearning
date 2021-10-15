package cn.zju.zuochengyun.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code7_CountOfSmallerNumbersAfterSelf {

	public static int max = 200000;
	public static int[] left = new int[max];
	public static int[] right = new int[max];
	public static int[] sum = new int[max];
	public static int cnt = 1;
	public static int size = 0;

	public static void add(int c, int l, int r, int i, int v) {
		if (l == r) {
			sum[c] += v;
		} else {
			int mid = (l + r) / 2;
			if (i <= mid) {
				if (left[c] == 0) {
					left[c] = ++cnt;
				}
				add(left[c], l, mid, i, v);
			} else {
				if (right[c] == 0) {
					right[c] = ++cnt;
				}
				add(right[c], mid + 1, r, i, v);
			}
			sum[c] = sum[left[c]] + sum[right[c]];
		}
	}

	public static int sum(int c, int l, int r, int s, int e) {
		if (sum[c] == 0 || (s <= l && r <= e)) {
			return sum[c];
		}
		int mid = (l + r) / 2;
		if (e <= mid) {
			return sum(left[c], l, mid, s, e);
		} else if (s > mid) {
			return sum(right[c], mid + 1, r, s, e);
		} else {
			return sum(left[c], l, mid, s, e) + sum(right[c], mid + 1, r, s, e);
		}
	}

	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			ans.add(0);
		}
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i] = new int[] { nums[i], i };
		}
		Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
		Arrays.fill(left, 1, n << 1, 0);
		Arrays.fill(right, 1, n << 1, 0);
		Arrays.fill(sum, 1, n << 1, 0);
		cnt = 1;
		size = n;
		for (int[] num : arr) {
			ans.set(num[1], sum(1, 1, n, num[1] + 1, n));
			add(1, 1, n, num[1] + 1, 1);
		}
		return ans;
	}

}
