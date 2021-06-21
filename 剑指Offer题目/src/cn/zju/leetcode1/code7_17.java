package cn.zju.leetcode1;

public class code7_17 {
    public int[] printNumbers1(int n) {
        int size = (int)Math.pow(10,n);
        int[] res = new int[size - 1];
        for (int i = 0; i < size - 1; i++) {
            res[i] = i + 1;
        }
        return res;
    }
    StringBuilder res;
    int nine = 0, count = 0, start, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String printNumbers(int n) {
        this.n = n;
        res = new StringBuilder();
        num = new char[n];
        start = n - 1;
        dfs(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
    void dfs(int x) {
        if (x == n) {
            String s = String.valueOf(num).substring(start);
            if (!s.equals("0")) res.append(s + ",");
            if (n - start == nine) start--;
            return;
        }
        for (char c : loop) {
            if (c == '9') nine++;
            num[x] = c;
            dfs(x + 1);
        }
        nine--;
    }
}
