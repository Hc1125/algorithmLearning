package cn.zju.zuochengyun.ExclusiveOR;

/**
 * a ^ a = 0 , 0 ^ a = a
 * 异或运算符合交换律和结合律
 * 一个数组中有一个数出现了奇数次， 其他数都出现了偶数次，怎么找到并打印这个数
 * 把所有数字异或起来的结果就是出现奇数次的数字
 */
public class code1_EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (~eor + 1); //提取出最右侧的1
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne) != 0){
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
    //返回这个数在二进制中有几个1
    public static int bit1counts(int N){
        int count = 0;
        while(N != 0){
            int rightOne = N & (-N);
            count++;
            N ^= rightOne;
        }
        return count;
    }


}
