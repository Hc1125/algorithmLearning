package cn.zju.leetcode;

public class code2_91 {
    public static int numDecodings(String s) {
        if (s.equals(null) || s.length() < 1 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        int pre = 1;
        int cur = 0;
        if (chars[1] == '0') {
            if (chars[0] == '1' || chars[0] == '2') {
                cur = 1;
            } else {
                return 0;
            }
        } else if (chars[0] == '1') {
            cur = 2;
        } else if (chars[0] == '2') {
            if (chars[1] >= '0' && chars[1] <= '6') {
                cur = 2;
            } else {
                cur = 1;
            }
        } else {
            cur = 1;
        }
        for (int i = 2; i < n; i++) {
            int temp = cur;
            if (chars[i] == '0') {
                if (chars[i - 1] == '1' || chars[i - 1] == '2') {
                    cur = pre;
                    pre = temp;
                } else {
                    return 0;
                }
            } else if (chars[i - 1] == '1') {
                cur = pre + cur;
                pre = temp;
            } else if (chars[i - 1] == '2' && chars[i] >= '0' && chars[i] <= '6') {
                cur = pre + cur;
                pre = temp;
            } else {
                cur = cur;
                pre = temp;
            }
        }
        return cur;
    }

    public static int numDecodings1(String s) {
        int n = s.length();
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; i++) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0' <= 26) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(1);
    }

}
