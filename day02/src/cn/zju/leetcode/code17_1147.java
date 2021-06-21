package cn.zju.leetcode;

public class code17_1147 {
    public int longestDecomposition(String text){
        char[] chars = text.toCharArray();
        int n = chars.length;
        int num = 0, i = 0, j = n - 1, len = 0;
        while(i+len < j){
            while(chars[i]!=chars[j] && i + len < j){
                j--;
                len++;
            }
            if(i + len >= j){
                return (++num);
            }else {
                int k;
                for (k = 1; k <= len; k++) {
                    if(chars[i+k] != chars[j+k]){
                        break;
                    }
                }
                if(k > len){
                    num += 2;
                    j--;
                    i+=(len+1);
                    len = 0;
                }else{
                    j--;
                    len++;
                }
            }
        }
        if(i > j){
            return num;
        }else {
            return (++num);
        }
    }
}
