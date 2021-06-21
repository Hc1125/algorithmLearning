package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class code18_1647 {
    public int minDeletions(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[chars[i] - 'a']++;
        }
        Arrays.sort(arr);
        int res = 0;
        for (int i = 24; i >= 0; i--) {
            while (arr[i] > 0 && arr[i] >= arr[i + 1]) {
                arr[i]--;
                res++;
            }
        }
        return res;
    }
    public static int minDeletions1(String s) {
        int[] a = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            a[c - 'a']++;
        }
        Set<Integer> h = new HashSet<>();
        int res = 0;
        for (int i : a) {
            if (i != 0) {
                while (h.contains(i)) {
                    i--;
                    res++;
                }
                if (i != 0) h.add(i);
            }
        }
        return res;
    }
}
