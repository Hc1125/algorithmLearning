package cn.zju.leetcode7;

import java.util.*;

public class code15_75_RelativeSortArray {
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        int upper = Arrays.stream(arr1).max().getAsInt();
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; i++) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; x++) {
            for (int i = 0; i < frequency[x]; i++) {
                ans[index++] = x;
            }
        }
        return ans;
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr2.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(arr2[i], i);
        }
        List<Integer> extra = new ArrayList<>();
        for (int i : arr1) {
            if (map.containsKey(i)) {
                cnt[map.get(i)]++;
            } else {
                extra.add(i);
            }
        }
        Collections.sort(extra);
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                ans[index++] = arr2[i];
            }
        }
        for (int num : extra) {
            ans[index++] = num;
        }
        return ans;
    }
}
