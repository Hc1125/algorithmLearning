package cn.zju.group3;

public class code4_639_DecodeWaysII {
    public int numDecodings(String s) {
        int base = 1000000007;
        char[] str = s.toCharArray();
        int n = str.length;
        long a = 1;
        long b = 1;
        long c = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                c = b * (str[i] == '*' ? 9 : 1);
                if (str[i] == '1' || str[i] == '2' || str[i] == '*') {
                    if (i + 1 < n) {
                        if (str[i + 1] == '*') {
                            c += a * (str[i] == '*' ? 15 : (str[i] == '1' ? 9 : 6));
                        } else {
                            if (str[i] == '*') {
                                c += a * (str[i + 1] < '7' ? 2 : 1);
                            } else {
                                c += a * ((str[i] - '0') * 10 + (str[i + 1] - '0') < 27 ? 1 : 0);
                            }
                        }
                    }
                }
            }
            c %= base;
            a = b;
            b = c;
            c = 0;
        }
        return (int) b;
    }
}
