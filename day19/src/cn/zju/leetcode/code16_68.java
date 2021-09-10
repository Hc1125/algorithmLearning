package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code16_68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        int right = 0;
        while (true) {
            int left = right;
            int sumLen = 0;
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }
            if (right == n) {
                StringBuilder sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }
            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;
            if (numWords == 1) {
                StringBuilder sb = new StringBuilder(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuilder sb = new StringBuilder();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1)));
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces)));
            ans.add(sb.toString());
        }
    }

    public String blank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public StringBuilder join(String[] words, int left, int right, String sep) {
        StringBuilder sb = new StringBuilder(words[left]);
        for (int i = left + 1; i < right; i++) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }
}
