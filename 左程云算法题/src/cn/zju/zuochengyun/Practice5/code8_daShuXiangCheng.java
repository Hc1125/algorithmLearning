package cn.zju.zuochengyun.Practice5;

public class code8_daShuXiangCheng {
    public static int[] multiply(int[] ints1, int[] ints2) {
        int[] result = new int[ints1.length + ints2.length + 1];
        for (int i = 0; i < ints1.length; i++) {
            for (int j = 0; j < ints2.length; j++) {
                result[i + j] += ints1[i] * ints2[j];
            }
        }
        boolean end = false;
        int endPos = 0;
        for (int i = result.length - 1; i > 0; i--) {
            if (!end && result[i] != 0) {
                end = true;
                endPos = i;
            }
            result[i - 1] += result[i] / 10;
            result[i] %= 10;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= endPos; i++) {
            ans.append(result[i]);
        }
        System.out.println(ans);
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = {6,7,8,9};
        System.out.println(((long)12345 * 6789));
        multiply(a, b);
    }
}
