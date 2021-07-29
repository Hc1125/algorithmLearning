package cn.zju.leetcode;

import java.util.Arrays;

public class code10_1170 {
    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] arr = new int[queries.length];
        int[] match = new int[words.length];
        int index = 0;
        for (String query : queries) {
            int[] count = new int[26];
            for (int i = 0; i < query.length(); i++) {
                count[query.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    arr[index++] = count[i];
                    break;
                }
            }
        }
        index = 0;
        for (String word : words) {
            int[] count = new int[26];
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    match[index++] = count[i];
                    break;
                }
            }
        }
        Arrays.sort(match);
        int[] ans = new int[queries.length];
        int n = words.length;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = n - binarySearch(arr[i], match);
        }
        return ans;
    }

    public static int binarySearch(int target, int[] arr) {
        int l = 0, r = arr.length - 1;
        int mid = 0;
        if (target >= arr[r]) {
            return r + 1;
        }
        while (l < r) {
            mid = ((r - l) >> 1) + l;
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
}
