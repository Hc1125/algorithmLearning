package cn.zju.zuochengyun.Practice1;

/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。现在可以
 * 选择任意一个正方形然后用这两种颜色的任意一种进行染色，这个正方形的颜色将会被覆盖。
 * 目标是在完成染色之后，每个红色R都比绿色G距离最左侧近。返回至少需要涂染几个正方形。
 * 如样例所示：s=RGRGR我们涂染之后变成RRRGG满足要求了，涂染的个数为2，
 * 没有比这个更好的方案。
 */
public class code4_ColorLeftRight {
    // RGRGR -> RRRGG
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int rAll = 0;
        for (int i = 0; i < n; i++) {
            rAll += (chars[i] == 'R' ? 1 : 0);
        }
        int ans = rAll;
        int left = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            left += (chars[i] == 'G' ? 1 : 0);
            rAll -= (chars[i] == 'R' ? 1 : 0);
            ans = Math.min(ans, left + rAll);
        }
        ans = Math.min(ans, left + (chars[chars.length - 1] == 'G' ? 1 : 0));
        return ans;
    }
}
