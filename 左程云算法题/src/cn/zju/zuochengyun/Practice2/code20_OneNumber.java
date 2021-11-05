package cn.zju.zuochengyun.Practice2;

/**
 * 数位dp
 * 给定一个正数N，表示你在纸上写下1~N所有的数字
 * 返回在书写过程中，一共写下了多少个1
 */
public class code20_OneNumber {

    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }

    public static int solution2(int num) {
        if (num < 1) {
            return 0;
        }
        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        int tmp1 = powerBaseOfNum(len - 1);
        // num最高位
        int first = num / tmp1;
        // 最高1 N % tmp + 1
        // 最高位first  tmp1
        int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;

        // 除去最高位之外，剩下1的数量
        // 最高位1  10 ^ (k - 2) * (k - 1) * 1
        // 最高位为first  10 ^ (k - 2) * (k - 1) * first
        int otherOneNum = first * (len - 1) * (tmp1 / 10);
        return firstOneNum + otherOneNum + solution2(num % tmp1);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static int powerBaseOfNum(int base) { return (int) Math.pow(10, base); }
}
