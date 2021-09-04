package cn.zju.leetcode;

public class code12_470 {
    public int rand7() {
        return 1;
    }
    public int rand10I() {
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += rand7();
        }
        return ans % 10 + 1;
    }
    public int rand10II() {
        int a, b, idx;
        while (true) {
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
            if (idx <= 40) {
                return 1 + (idx - 1) % 10;
            }
            a = idx - 40;
            b = rand7();
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7;
            if (idx <= 60) {
                return 1 + (idx - 1) % 10;
            }
            a = idx - 60;
            b = rand7();
            // get uniform dist from 1 - 21
            idx = b + (a - 1) * 7;
            if (idx <= 20) {
                return 1 + (idx - 1) % 10;
            }
        }
    }
}
