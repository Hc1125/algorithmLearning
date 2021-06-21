package cn.zju.leetcode2;

public class code14_58_I {
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) continue;
            sb.append(strs[i] + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String s = " hello world! ";
        String[] strings = s.split(" ");
        System.out.println(strings[0].length());
        System.out.println(strings[0].equals(" "));
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
