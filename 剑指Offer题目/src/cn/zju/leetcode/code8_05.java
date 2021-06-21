package cn.zju.leetcode;

public class code8_05 {
    public static void main(String[] args) {
        char[] chars = new char[3];
        System.out.println(chars[0]);
    }
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        String ans = "";
        for (char c : chars) {
            if (c == ' ') {
                ans += "%20";
            } else {
                ans += c;
            }
        }
        return ans;
    }
    public String replaceSpace1(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        return new String(array, 0, size);
    }
}
