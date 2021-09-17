package cn.zju.zuochengyun.Practice2;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个字符串中找到没有重复字符子串中最长的长度
 * 例如：
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 */
public class code13_LogestNoRepeatSubString {
    public static int longestNoRepeatSubString(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < N; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s1 = "xxxxxxsxxxxxx";
        System.out.println(longestNoRepeatSubString(s1));
    }
}
