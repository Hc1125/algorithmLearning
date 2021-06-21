package cn.zju.leetcode;

public class code11_135 {
    public static void main(String[] args) {

    }
    private static int candy1(int[] ratings){
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if(i>0 && ratings[i]>ratings[i-1]){
                left[i] = left[i-1]+1;
            }else{
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for(int i = n-1;i >= 0;i--){
            if(i < n-1 && ratings[i] > ratings[i+1]){
                right++;
            }else{
                right = 1;
            }
            ret += Math.max(left[i],right);
        }
        return ret;
    }
    private static int candy2(int[] ratings){
        if(ratings.length == 0){
            return 0;
        }
        int count = 1, upcount = 1, downcount = 0;
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i-1] < ratings[i]){
                if(downcount!=0){
                    upcount = 1;
                }
                downcount = 0;
                upcount++;
                count += upcount;
            }else if(ratings[i-1] == ratings[i]){
                upcount = 1;
                downcount = 0;
                count++;
            }else{
                downcount++;
                if(downcount == upcount){
                    downcount++;
                }
                count += downcount;
            }
        }
        return count;
    }
}
