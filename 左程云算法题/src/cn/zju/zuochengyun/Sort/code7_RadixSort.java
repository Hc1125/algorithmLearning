package cn.zju.zuochengyun.Sort;

public class code7_RadixSort {
    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // arr[L..R]排序， digit最大数的十进制位数
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { //有多少位就进出几次
            // 10个空间
            // count[0] 当前位（d位）是0的数字有多少个
            // count[1] 当前位（d位）是(0和1 )的数字有多少个
            // count[2] 当前位（d位）是(0、1和2)的数字有多少个
            // count[i] 当前位（d位）是(0~i)的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    public static int getDigit(int num, int digit) {
        int index = (int)Math.pow(10, digit - 1);
        return (num / index) % 10;
    }

}
