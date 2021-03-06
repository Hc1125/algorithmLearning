package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code4_1239 {
    int ans = 0;
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }
                mask |= 1 << ch;
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }
        backtrack(masks, 0, 0);
        return ans;
    }

    public void backtrack(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        if ((mask & masks.get(pos)) == 0) {
            backtrack(masks, pos + 1, mask | masks.get(pos));
        }
        backtrack(masks, pos + 1, mask);
    }
}
