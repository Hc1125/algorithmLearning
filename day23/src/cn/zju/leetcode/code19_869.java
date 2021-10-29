package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code19_869 {
    Set<String> set = new HashSet<>();
    public boolean reorderedPowerOf2(int n) {
        init();
        return set.contains(generate(n));
    }
    public void init() {
        for (int i = 1; i <= 1e9; i <<= 1) {
            set.add(generate(i));
        }
    }
    public String generate(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            cnt[n % 10]++;
            n /= 10;
        }
        return new String(cnt);
    }
}
