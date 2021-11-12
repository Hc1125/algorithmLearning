package cn.zju.leetcode8;

import java.util.ArrayList;
import java.util.List;

public class code7_87 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n < 4) {
            return ans;
        }
        int[] arr = new int[4];
        process(s, 0, n, 0, ans, arr);
        return ans;
    }
    public void process(String s, int index, int n, int id, List<String> ans, int[] arr) {
        if (id == 4) {
            if (index == n) {
                StringBuilder ip = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    ip.append(arr[i]);
                    if (i < 3) {
                        ip.append('.');
                    }
                }
                ans.add(ip.toString());
            }
            return;
        }
        if (index == n) {
            return;
        }
        if (s.charAt(index) == '0') {
            arr[id] = 0;
            process(s, index + 1, n, id + 1, ans, arr);
        }
        int addr = 0;
        for (int i = index; i < n; i++) {
            addr = addr * 10 + s.charAt(i) - '0';
            if (addr > 0 && addr <= 255) {
                arr[id] = addr;
                process(s, i + 1, n, id + 1, ans, arr);
            } else {
                break;
            }
        }
    }
}
