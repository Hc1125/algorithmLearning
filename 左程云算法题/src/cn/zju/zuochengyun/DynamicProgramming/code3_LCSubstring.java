package cn.zju.zuochengyun.DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class code3_LCSubstring {
    /**
     * 两个字符串的最长公共子串
     * 但不是最优解
     * 最优解为后缀数组解法
     */
    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < chs1.length; i++) {
            for (int j = 0; j < chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    public static String lcst2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int row = 0;
        int col = str2.length - 1;
        int max = 0;
        int end = 0;
        while (row < str1.length) {
            int i = row;
            int j = col;
            System.out.println(j);
            int len = 0;
            while (i < str1.length && j < str2.length) {
                if (str1[i] != str2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return s1.substring(end - max + 1, end + 1);
    }


    /**
     * 两个字符串的最长公共子串，包含可忽视字符
     */

    public static String lcstIgnore(String s1, String s2, char[] chars) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char aChar : chars) {
            set.add(aChar);
        }
        int row = 0;
        int col = str2.length - 1;
        int max = 0;
        int end = 0;
        boolean isFirst = true;
        while (row < str1.length) {
            int i = row;
            int j = col;
            int len1 = 0;
            int len2 = 0;
            while (i < str1.length || j < str2.length) {
                if ((i < str1.length && set.contains(str1[i])) || (j < str2.length && set.contains(str2[j]))) {
                    if (i < str1.length && set.contains(str1[i])) {
                        i++;
                        len1++;
                    }
                    if (j < str2.length && set.contains(str2[j])) {
                        j++;
                        len2++;

                    }
                } else if (i <str1.length && j < str2.length && str1[i] == str2[j]) {
                    i++;
                    j++;
                    len1++;
                    len2++;
                } else {
                    len1 = 0;
                    len2 = 0;
                    i++;
                    j++;
                }
                if (len1 > max || len2 > max) {
                    if (len1 > len2) {
                        end = i;
                        max = len1;
                        isFirst = true;
                    } else {
                        end = j;
                        max = len2;
                        isFirst = false;
                    }
                }
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return isFirst ? s1.substring(end - max, end) : s2.substring(end - max, end);
    }

    public static void main(String[] args) {
        String s1 = "abc%%###%";
        String s2 = "zxab%c%%%%";
        char[] c = {'%', '#'};
        System.out.println(lcstIgnore(s1, s2, c));
    }
}
