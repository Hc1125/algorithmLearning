package cn.zju.zuochengyun.ExclusiveOR;

/**
 * 一个数组中只有一种数出现K次，其他数都出现了M次
 * M > 1, K < M
 * 找到，出现了K次的数
 */
public class code2_KM {
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        //t[i] i位置的1出现了几个
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m == 0) {
                continue;
            }
            if (t[i] % m == k) {
                ans |= (1 << i);
            } else {
                return -1;
            }
        }
        return ans;
    }
}
