package cn.zju.zuochengyun.Sort.MergeProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你`一个整数数组 nums ，按要求返回一个新数组 counts 。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 */
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

	public static List<Integer> countSmaller1(int[] nums) {
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


	public class Node {
		public int index;
		public int value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	public List<Integer> countSmaller2(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null) {
			return ans;
		}
		for (int i = 0; i < nums.length; i++) {
			ans.add(0);
		}
		if (nums.length < 2) {
			return ans;
		}
		Node[] arr = new Node[nums.length];
		for (int i = 0; i < nums.length; i++) {
			arr[i] = new Node(i, nums[i]);
		}
		process(arr, 0, arr.length - 1, ans);
		return ans;
	}

	public void process(Node[] arr, int l, int r, List<Integer> ans) {
		if (l == r) {
			return;
		}
		int m = l + ((r - l) >> 1);
		process(arr, l, m, ans);
		process(arr, m + 1, r, ans);
		merge(arr, l, m, r, ans);
	}

	public void merge(Node[] arr, int l, int m, int r, List<Integer> ans) {
		Node[] help = new Node[r - l + 1];
		int i = help.length - 1;
		int p1 = m, p2 = r;
		while (p1 >= l && p2 > m) {
			if (arr[p1].value > arr[p2].value) {
				ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - m);
			}
			help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
		}
		while (p1 >= l) {
			help[i--] = arr[p1--];
		}
		while (p2 > m) {
			help[i--] = arr[p2--];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

}
