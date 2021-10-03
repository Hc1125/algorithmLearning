package week1_2021_09_26;

import java.util.*;

/**
 * 给你一个字符串 s ，它 只 包含数字 0-9 ，加法运算符 '+' 和乘法运算符 '*' ，这个字符串表示一个 合法 的只含有 个位数数字 的数学表达式（比方说 3+5*2）。有 n 位小学生将计算这个数学表达式，并遵循如下 运算顺序 ：
 *
 * 按照 从左到右 的顺序计算 乘法 ，然后
 * 按照 从左到右 的顺序计算 加法 。
 * 给你一个长度为 n 的整数数组 answers ，表示每位学生提交的答案。你的任务是给 answer 数组按照如下 规则 打分：
 *
 * 如果一位学生的答案 等于 表达式的正确结果，这位学生将得到 5 分。
 * 否则，如果答案由 一处或多处错误的运算顺序 计算得到，那么这位学生能得到 2 分。
 * 否则，这位学生将得到 0 分。
 * 请你返回所有学生的分数和。
 *
 * 3 <= s.length <= 31
 * s 表示一个只包含 0-9 ，'+' 和 '*' 的合法表达式。
 * 表达式中所有整数运算数字都在闭区间 [0, 9] 以内。
 * 1 <= 数学表达式中所有运算符数目（'+' 和 '*'） <= 15
 * 测试数据保证正确表达式结果在范围 [0, 1000] 以内。
 * n == answers.length
 * 1 <= n <= 104
 * 0 <= answers[i] <= 1000
 *
 */
public class code2_5884 {
    public int scoreOfStudents(String s, int[] answers) {
        int right = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        LinkedList<Integer> stack = new LinkedList<>();
        // 计算正确答案
        stack.addLast(str[0] - '0');
        // 第一个元素入栈
        for (int i = 1; i < n; i += 2) {
            // 加法元素直接入栈，乘法元素直接和栈顶得到结果重新入栈
            if (str[i] == '+') {
                stack.addLast(str[i + 1] - '0');
            } else {
                stack.addLast(stack.pollLast() * (str[i + 1] - '0'));
            }
        }
        // 得到正确结果
        while (!stack.isEmpty()) {
            right += stack.pollLast();
        }
        // 化简字符串中运算符和数字
        int[] numbers = new int[(n + 1) / 2];
        boolean[] operations = new boolean[n / 2];
        for (int i = 0; i < n; i++) {
            if (str[i] == '+') {
                operations[i / 2] = true;
            } else if (str[i] != '*') {
                numbers[i / 2] = str[i] - '0';
            }
        }
        int len = (n + 1) / 2;
        Set<Integer>[][] dp = new HashSet[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = new HashSet<>();
            dp[i][i].add(numbers[i]);
        }
        // 枚举结束位置
        for (int j = 1; j < len; j++) {
            // 枚举开始位置
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = new HashSet<>();
                // 枚举中间界限位置
                for (int k = i; k < j; k++) {
                    // 根据中间位置运算符，计算连接后的结果
                    // 剪枝：因为学生猜测结果均在 [0,1000]，因此超限的值可以直接忽略
                    if (operations[k]) {
                        for (int x : dp[i][k]) {
                            for (int y : dp[k + 1][j]) {
                                if (x + y <= 1000) dp[i][j].add(x + y);
                            }
                        }
                    } else {
                        for (int x : dp[i][k]) {
                            for (int y : dp[k + 1][j]) {
                                if (x * y <= 1000) dp[i][j].add(x * y);
                            }
                        }
                    }
                }
            }
        }
        // 统计答案
        int ans = 0;
        for (int x : answers) {
            if (x == right) ans += 5;
            else if (dp[0][len - 1].contains(x)) ans += 2;
        }
        return ans;
    }
}
