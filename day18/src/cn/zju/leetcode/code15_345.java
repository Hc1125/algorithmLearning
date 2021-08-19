package cn.zju.leetcode;

public class code15_345 {
    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            while (i < j && !isValid(chars[i])) {
                i++;
            }
            while (j > i && !isValid(chars[j])) {
                j--;
            }
            if (i < j) {
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
                j--;
            }
        }
        return String.valueOf(chars);
    }

    public static boolean isValid(char c) {
        return c == 'a' || c == 'A' || c == 'e' || c == 'E' ||
                c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U';
    }
}
