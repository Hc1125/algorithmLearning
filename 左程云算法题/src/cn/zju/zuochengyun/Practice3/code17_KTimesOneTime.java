package cn.zju.zuochengyun.Practice3;

/**
 * 给定一个数组arr，已知除了一种数只出现1次之外
 * 剩下所有的数都出现了k次，如何使用O(1)的额外空间，找到这个数
 */
public class code17_KTimesOneTime {
    public static int onceNum(int[] arr, int k) {
        int[] e0 = new int[32];
        for (int i = 0; i < arr.length; i++) {
            // 当前数是arr[i]，请把arr[i]变成K进制的形式，每一位累加到e0
            setExclusive(e0, arr[i], k);
        }
        int res = getNumFromKSysNum(e0, k);
        return res;
    }

    public static void setExclusive(int[] e0, int value, int k) {
        int[] curKSysNum = getKSysNumFromNum(value, k);
        for (int i = 0; i < e0.length; i++) {
            e0[i] = (e0[i] + curKSysNum[i]) % k;
        }
    }

    public static int[] getKSysNumFromNum(int value, int k) {
        int[] res = new int[32];
        int index = 0;
        while (value != 0) {
            res[index++] = value % k;
            value /= k;
        }
        return res;
    }

    public static int getNumFromKSysNum(int[] e0, int k) {
        int res = 0;
        for (int i = e0.length - 1; i >= 0; i--) {
            res = res * k + e0[i];
        }
        return res;
    }
}
