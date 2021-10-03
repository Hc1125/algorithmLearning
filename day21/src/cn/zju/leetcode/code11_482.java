package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code11_482 {
    public String licenseKeyFormatting1(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                cnt++;
                ans.append(Character.toUpperCase(s.charAt(i)));
                if (cnt % k == 0) {
                    ans.append("-");
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }
    public String licenseKeyFormatting2(String s, int k) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                if (Character.isLowerCase(c)) {
                    list.add(Character.toUpperCase(c));
                } else {
                    list.add(c);
                }
            }
        }
        if (list.size() == 0) {
            return "";
        }
        int size = list.size();
        int begin = size % k;
        if (begin == 0) {
            begin += k;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < begin; i++) {
            ans.append(list.get(i));
        }
        if (size / k > 0) {
            ans.append("-");
            int p = 0;
            for (int i = begin; i < list.size(); i++) {
                p++;
                ans.append(list.get(i));
                if (p % k == 0) {
                    ans.append("-");
                }
            }
            ans.deleteCharAt(ans.length() - 1);
        }

        return ans.toString();
    }
}
