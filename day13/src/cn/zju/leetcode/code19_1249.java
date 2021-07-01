package cn.zju.leetcode;

public class code19_1249 {
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                num++;
            } else if (chars[i] == ')') {
                num--;
            }
            if (num < 0) {
                num = 0;
                chars[i] = 'A';
            }
        }
        int i = chars.length - 1;
        while (num > 0) {
            if (chars[i] == '(') {
                chars[i] = 'A';
                num--;
            }
            i--;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c != 'A') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
