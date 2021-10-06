package cn.zju.zuochengyun.QuadInequality;
/**
 * 四边形不等式技巧特征
 *
 * 1，两个可变参数的区间划分问题
 * 2，每个格子有枚举行为
 * 3，当两个可变参数固定一个，另一个参数和答案之间存在单调性关系
 * 4，而且往往是反向单调关系
 * 5，枚举加速的位置对:上+右，或者,左+下
 * 6，不要证明!用对数器验证!
 * 7，可以把时间复杂度降低一阶
 */

/**
 * 一个非负数组
 * 将数组分成两部分，获取在最优划分下，两部分累加和较小值的最大值
 */
public class code1_BestSplitForAll {

	public static int bestSplit1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int ans = 0;
		for (int s = 0; s < N - 1; s++) {
			int sumL = 0;
			for (int L = 0; L <= s; L++) {
				sumL += arr[L];
			}
			int sumR = 0;
			for (int R = s + 1; R < N; R++) {
				sumR += arr[R];
			}
			ans = Math.max(ans, Math.min(sumL, sumR));
		}
		return ans;
	}

	public static int bestSplit2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int sumAll = 0;
		for (int num : arr) {
			sumAll += num;
		}
		int ans = 0;
		int sumL = 0;
		// [0...s]  [s+1...N-1]
		for (int s = 0; s < N - 1; s++) {
			sumL += arr[s];
			int sumR = sumAll - sumL;
			ans = Math.max(ans, Math.min(sumL, sumR));
		}
		return ans;
	}

	public static int[] randomArray(int len, int max) {
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		return ans;
	}

	public static void main(String[] args) {
		int N = 20;
		int max = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			int[] arr = randomArray(len, max);
			int ans1 = bestSplit1(arr);
			int ans2 = bestSplit2(arr);
			if (ans1 != ans2) {
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");
	}

}