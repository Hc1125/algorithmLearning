package cn.zju.leetcode;


import java.util.*;

public class code5_1178 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;
            int mask = 0;
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;// 获得mask所有1的各种组合组成的数
            } while (subset != mask);
            ans.add(total);
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 11;
        int b = a;
        int ans = 0;
        do {
            b = (b - 1) & a;
            System.out.println(b);
            ans++;
        } while (b != a);
        System.out.println(ans + "----------");
    }
}
