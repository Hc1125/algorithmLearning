package cn.zju.zuochengyun.Practice1;

/**
 * 括号有效配对：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：(())()()(()())
 * 无效的：(())(
 * 问题一：怎么判断一个括号字符串有效
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
public class code2_NeedParentheses {
    public static boolean valid(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
    public static int needParenthneses(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                need++;
                count++;
            }
        }
        return count + need;
    }
}
