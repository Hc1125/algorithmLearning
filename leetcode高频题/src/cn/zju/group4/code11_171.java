package cn.zju.group4;

public class code11_171 {
    public int titleToNumber(String columnTitle) {
        char[] str = columnTitle.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            ans = ans * 26 + (str[i] - 'A') + 1;
        }
        return ans;
    }
}
