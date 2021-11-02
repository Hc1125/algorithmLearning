package week8_2021_10_31;

import java.util.HashSet;
import java.util.Set;

/**
 * 原字符串由小写字母组成，可以按下述步骤编码：
 *
 * 任意将其 分割 为由若干 非空 子字符串组成的一个 序列 。
 * 任意选择序列中的一些元素（也可能不选择），然后将这些元素替换为元素各自的长度（作为一个数字型的字符串）。
 * 重新 顺次连接 序列，得到编码后的字符串。
 * 例如，编码 "abcdefghijklmnop" 的一种方法可以描述为：
 *
 * 将原字符串分割得到一个序列：["ab", "cdefghijklmn", "o", "p"] 。
 * 选出其中第二个和第三个元素并分别替换为它们自身的长度。序列变为 ["ab", "12", "1", "p"] 。
 * 重新顺次连接序列中的元素，得到编码后的字符串："ab121p" 。
 * 给你两个编码后的字符串 s1 和 s2 ，由小写英文字母和数字 1-9 组成。如果存在能够同时编码得到 s1 和 s2 原字符串，返回 true ；否则，返回 false。
 *
 * 注意：生成的测试用例满足 s1 和 s2 中连续数字数不超过 3 。
 *
 * 输入：s1 = "internationalization", s2 = "i18n"
 * 输出：true
 * 解释："internationalization" 可以作为原字符串
 * - "internationalization"
 *   -> 分割：      ["internationalization"]
 *   -> 不替换任何元素
 *   -> 连接：      "internationalization"，得到 s1
 * - "internationalization"
 *   -> 分割：      ["i", "nternationalizatio", "n"]
 *   -> 替换：      ["i", "18",                 "n"]
 *   -> 连接：      "i18n"，得到 s2
 */
public class code1_5917 {
    /**
     * 我们用dp[i][j]dp[i][j]表示将s1的前i个字母和s2的前j个字母匹配且不发生冲突时，可能的长度差值。
     *
     * 可以看到，存在以下的转移：
     * dp[i][j] -> dp[p][j]  x -> x + s1[i][p] 要求s1[i][p]是一个数字
     * dp[i][j] -> dp[i][q]  x -> x + s2[j][q] 要求s2[j][q]是一个数字
     * dp[i][j] -> dp[i + 1][j] x -> x + 1   要求s1[i]是一个字母，并且x<0,从而保证这个字母可以被s2的剩余长度匹配掉
     * dp[i][j] -> dp[i][j + 1] x -> x - 1   要求s2[j]是一个字母，并且x>0,从而保证这个字母可以被s1的剩余长度匹配掉
     * dp[i][j] -> dp[i + 1][j + 1] x -> x   要求s1[i] == s2[j]且都为字母，并且x == 0
     *
     * 最后，我们检查dp[N][M]是否包含0即可。
     *
     * 时间复杂度O(C * N^2) C:[-1000, 1000]
     *  )。
     */
    public boolean possiblyEquals(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        Set<Integer>[][] dp = new Set[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        dp[0][0].add(0);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int delta : dp[i][j]) {
                    int num = 0;
                    for (int p = i; p < Math.min(i + 3, n); p++) {
                        if (Character.isDigit(s1.charAt(p))) {
                            num = num * 10 + s1.charAt(p) - '0';
                            dp[p + 1][j].add(delta + num);
                        } else {
                            break;
                        }
                    }
                    num = 0;
                    for (int q = j; q < Math.min(j + 3, m); q++) {
                        if (Character.isDigit(s2.charAt(q))) {
                            num = num * 10 + s2.charAt(q) - '0';
                            dp[i][q + 1].add(delta - num);
                        } else {
                            break;
                        }
                    }
                    if (i < n && delta < 0 && !Character.isDigit(s1.charAt(i))) {
                        dp[i + 1][j].add(delta + 1);
                    }
                    if (j < m && delta > 0 && !Character.isDigit(s2.charAt(j))) {
                        dp[i][j + 1].add(delta - 1);
                    }
                    if (i < n && j < m && delta == 0 && s1.charAt(i) == s2.charAt(j)) {
                        dp[i + 1][j + 1].add(0);
                    }
                }
            }
        }
        return dp[n][m].contains(0);
    }
}
