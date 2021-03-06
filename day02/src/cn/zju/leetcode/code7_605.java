package cn.zju.leetcode;

public class code7_605 {
    private static boolean canPlaceFlowers(int[] flowerbed, int n){
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if(flowerbed[i] == 1){
                if (prev<0){
                    count += i/2;
                }else {
                    count += (i-prev-2)/2;
                }
                if(count >= n){
                    return true;
                }
                prev = i;
            }
        }
        if(prev < 0){
            count += (m+1)/2;
        }else{
            count += (m-1-prev)/2;
        }
        return count >= n;
    }
}
