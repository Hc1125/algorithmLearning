package cn.zju.leetcode;

import javax.print.event.PrintEvent;

public class code16_443 {
    public int compress1(char[] chars) {
        int i = 0, j = 1;
        int count = 1;
        int n = chars.length;
        if (n == 1) {
            return 1;
        }
        while (j < n) {
            while (j < n && chars[j] == chars[j - 1]) {
                j++;
                count++;
            }
            if (count > 1) {
                chars[i++] = chars[j - 1];
                String s = String.valueOf(count);
                for (int k = 0; k < s.length(); k++) {
                    chars[i++] = s.charAt(k);
                }
            } else {
                chars[i++] = chars[j - 1];
            }
            if (j == n - 1) {
                chars[i++] = chars[n - 1];
            }
            count = 1;
            j++;
        }
        return i;
    }

    public int compress2(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }
    public void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = tmp;
        }
    }
}
