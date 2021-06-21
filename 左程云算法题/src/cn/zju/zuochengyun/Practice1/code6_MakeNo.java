package cn.zju.zuochengyun.Practice1;

/**
 * 给定一个正整数M，请构造出一个长度为M的数组arr，要求
 * 对任意的i，j，k三个位置，如果i<j<k，都有arr[i] + arr[k] != 2 * arr[j]
 * 返回构造出的arr
 */
public class code6_MakeNo {
    // 生成长度为size的达标数组
    // 达标：对于任意的 i < k < j，满足 [i] + [j] != [k] * 2
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[] {1};
        }
        // size
        // 一半长达标来
        // 7  :  4
        // 8  :  4
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++,i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }
}
