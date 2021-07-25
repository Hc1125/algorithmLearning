package cn.zju.leetcode;

public class code16_1736 {
    public static String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        int n = time.length();
        for (int i = 0; i < n; i++) {
            char c = time.charAt(i);
            if (c != '?') {
                sb.append(c);
            } else {
                if (i == 0) {
                    if (time.charAt(1) == '?') {
                        sb.append('2');
                    } else if (time.charAt(1) > '3') {
                        sb.append('1');
                    } else {
                        sb.append('2');
                    }
                } else if (i == 1) {
                    if (sb.charAt(0) < '2') {
                        sb.append('9');
                    } else {
                        sb.append('3');
                    }
                } else {
                    sb.append(i == 3 ? '5' : '9');
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String time = "2?:?0";
        System.out.println(maximumTime(time));
    }
}
