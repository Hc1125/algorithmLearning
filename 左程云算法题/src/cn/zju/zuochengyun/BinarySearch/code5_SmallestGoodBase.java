package cn.zju.zuochengyun.BinarySearch;
// leetcode483
public class code5_SmallestGoodBase {

	// ""4651" -> 4651
	public static String smallestGoodBase1(String n) {
		long num = Long.parseLong(n);
		// n这个数，需要从m位开始试，固定位数，一定要有m位！
		for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
			// num开m次方
			long l = (long) (Math.pow(num, 1.0 / m));
			long r = (long) (Math.pow(num, 1.0 / (m - 1))) + 1L;
			while (l <= r) {
				long k = l + ((r - l) >> 1);
				long sum = 0L;
				long base = 1L;
				for (int i = 0; i < m && sum <= num; i++) {
					sum += base;
					base *= k;
				}
				if (sum < num) {
					l = k + 1;
				} else if (sum > num) {
					r = k - 1;
				} else {
					return String.valueOf(k);
				}
			}
		}
		return String.valueOf(num - 1);
	}

	public String smallestGoodBase2(String n) {
		long nVal = Long.parseLong(n);
		int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
		for (int m = mMax; m > 1; m--) {
			// 事实上当位数固定，若某个k能是好进制，其将会等于 n ^ (1 / m)的整数部分
			int k = (int) Math.pow(nVal, 1.0 / m);
			long mul = 1, sum = 1;
			for (int i = 0; i < m; i++) {
				mul *= k;
				sum += mul;
			}
			if (sum == nVal) {
				return Integer.toString(k);
			}
		}
		return Long.toString(nVal - 1);
	}

}
