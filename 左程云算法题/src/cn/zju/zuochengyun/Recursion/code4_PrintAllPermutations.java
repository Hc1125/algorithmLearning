package cn.zju.zuochengyun.Recursion;

import java.util.ArrayList;
/*
    打印字符串的所有排列
 */
public class code4_PrintAllPermutations {
    public static ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        return res;
    }
    // str[0..i-1]已经做好决定的
    // str[i...]都有机会来到i位置
    // i到达终止位置
    public static void process(char[] str,int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process(str, i + 1, res);
            swap(str, i, j);
        }
    }
    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    // 不允许有重复排列
    public static void process2(char[] str,int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process2(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }
}
