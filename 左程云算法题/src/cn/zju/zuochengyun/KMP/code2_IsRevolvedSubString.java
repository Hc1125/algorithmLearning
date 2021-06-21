package cn.zju.zuochengyun.KMP;

public class code2_IsRevolvedSubString {
    /**
     * str2是否是str1的旋转串
     * "123456" -> "561234"是旋转串
     * "123456" ->"651234"不是旋转串
     */
    public boolean isRevolved(String str1, String str2) {
        return (str1 + str1).indexOf(str2) != -1;
    }
}
