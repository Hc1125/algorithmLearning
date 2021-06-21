package cn.zju.zuochengyun.DynamicProgramming;

import java.util.HashMap;

public class code5_StickerToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? - 1 : ans;
    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char c : str1) {
            count[c - 'a']++;
        }
        for (char c : str2) {
            count[c - 'a']--; 
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    stringBuilder.append((char) (i + 'a'));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化（用词频表替代贴纸数组）
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化（重要的剪枝！这一步也是贪心！）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            stringBuilder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化（重要的剪枝！这一步也是贪心！）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            stringBuilder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }
}
