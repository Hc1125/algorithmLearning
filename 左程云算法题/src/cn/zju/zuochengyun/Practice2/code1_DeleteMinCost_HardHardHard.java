package cn.zju.zuochengyun.Practice2;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串?比如s1 = "abcde"，s2 = "axbc"
 * 返回1。s2删掉'x'就是s1的子串了。
 */
public class code1_DeleteMinCost_HardHardHard {
    /**
     * s2删最少个数的字符变成s1的子串
     * 获得s2的所有子序列串，由大到小排列，去用kmp去算是否是s1的子串
     * 先找到的就是答案
     * 但是当s2的长度过长，会出现问题
     * O(N * 2 ^ M)
     */
    public static int minCost1(String s1, String s2) {
        List<String> s2Subs = new ArrayList<>();
        process(s2.toCharArray(), 0, "", s2Subs);
        s2Subs.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        for (String str : s2Subs) {
            if (str.length() > s1.length()) {
                continue;
            }
            if (s1.indexOf(str) != -1) {
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }
    public static void process(char[] str2, int index, String path, List<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        process(str2, index + 1, path, list);
        process(str2, index + 1, path + str2[index], list);
    }
    /**
     * 解法2
     * 生成所有s1的子串
     * 然后考察每个子串和s2的编辑距离（假设编辑距离只有删除动作且删除一个字符串的代价为1）
     * 如果s1的长度较小，s2长度较大，这个方法比较合适
     * O（N ^ 3 * M）
     */
    public static int minCost2(String s1, String s2) {
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                ans = Math.min(ans, distance(str2, s1.substring(start, end).toCharArray()));
            }
        }
        return ans;
    }

    // 求str2到s1sub的编辑距离
    // 假设编辑距离只有删除动作且删除一个字符的代价为1
    public static int distance(char[] str2, char[] s1sub) {
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }
    // 解法二的优化
    // O(N ^ 2 * M)
    public static int minCost3(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        char[] str2 = s2.toCharArray();
        char[] str1 = s1.toCharArray();
        int M = str2.length;
        int N = str1.length;
        int[][] dp = new int[M][N];
        int ans = M;
        for (int start = 0; start < N; start++) {  // 开始的列数
            dp[0][start] = str2[0] == str1[start] ? 0 : M;
            for (int row = 1; row < M; row++) {    // 把start列填好
                dp[row][start] = (str2[row] == str1[start] || dp[row - 1][start] != M) ? row : M;
            }
            ans = Math.min(ans, dp[M - 1][start]);
            /**
             * 以上把dp[...][start...start]的信息填好了
             * 以下要把dp[...][start...end]的信息填好
             */
            for (int end = start + 1; end < N && end - start < M; end++) {
                int first = end - start;
                dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] != M) ? 0 : M;
                for (int row = first + 1; row < M; row++) {
                    dp[row][end] = M;
                    if (dp[row - 1][end] != M) {
                        dp[row][end] = dp[row - 1][end] + 1;
                    }
                    if (dp[row - 1][end - 1] != M && str2[row] == str1[end]) {
                        dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
                    }
                }
                ans = Math.min(ans, dp[M - 1][end]);
            }
        }
        return ans;
    }

    // 时间复杂度O(N * M ^ 2)
    // 复杂度和方法三一样，但是思路截然不同
    public static int minCost4(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        HashMap<Character, ArrayList<Integer>> map1 = new HashMap<>();
        for (int i = 0; i < str1.length; i++) {
            ArrayList<Integer> list = map1.getOrDefault(str1[i], new ArrayList<Integer>());
            list.add(i);
            map1.put(str1[i], list);
        }
        int ans = 0;
        // 假设删除后的str2必以i位置开头
        // 那么查找i位置在str1上一共有几个，并对str1上的每个位置开始遍历
        // 再次遍历str2一次，看存在对应str1中i后续连续子串可容纳的最长长度
        for (int i = 0; i < str2.length; i++) {
            if (map1.containsKey(str2[i])) {
                ArrayList<Integer> keyList = map1.get(str2[i]);
                for (int j = 0; j < keyList.size(); j++) {
                    int cur1 = keyList.get(j) + 1;
                    int cur2 = i + 1;
                    int count = 1;
                    for (int k = cur2; k < str2.length && cur1 < str1.length; k++) {
                        if (str2[k] == str1[cur1]) {
                            cur1++;
                            count++;
                        }
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return s2.length() - ans;
    }
}
