package cn.zju.leetcode;

public class code17_38 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        char[] last = countAndSay(n - 1).toCharArray();
        int times = 1;
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < last.length; i++) {
            if (last[i] == last[i - 1]) {
                times++;
            } else {
                ans.append(times);
                ans.append(last[i - 1]);
                times = 1;
            }
        }
        ans.append(times);
        ans.append(last[last.length - 1]);
        return ans.toString();
    }
}
