package cn.zju.zuochengyun.GreedyAlgorithm;

public class code4_Light {
    /**
     * 字符串只有'X'和'.'构成
     * X表示不能放灯也不需要照明
     * .表示可以放灯，需要照明
     * 放一盏灯可以照明 i - 1, i 和 i + 1的位置
     * 给你一个字符串返回最少需要几盏灯
     */
    public static int minLight(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length) {
                    break;
                } else {
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }
}
