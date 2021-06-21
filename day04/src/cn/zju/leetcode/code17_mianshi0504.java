package cn.zju.leetcode;

public class code17_mianshi0504 {
    public int[] findClosedNumbers(int num){
        int[] target = new int[2];
        if(num == 0 || num == Integer.MAX_VALUE){
            return new int[]{-1,-1};
        }
        int temp = num;
        int countNum = 0;
        countNum = bit1counts(temp);
        int bigThanNum = num + 1;
        int countBigThanNum = 0;
        int smallThanNum = num - 1;
        int countSmallThanNum = 0;
        while(bigThanNum != Integer.MAX_VALUE){
            int tempBigThanNum = bigThanNum;
            countBigThanNum = bit1counts(bigThanNum);
            if(countBigThanNum == countNum){
                break;
            }else{
                bigThanNum++;
                countBigThanNum = 0;
            }
        }
        if(bigThanNum == Integer.MAX_VALUE){
            target[0] = -1;
        }else{
            target[0] = bigThanNum;
        }
        while(smallThanNum != 0){
            int tempSmallThanNum = smallThanNum;
            countSmallThanNum = bit1counts(smallThanNum);
            if(countSmallThanNum == countNum){
                break;
            }else{
                smallThanNum--;
                countSmallThanNum = 0;
            }
        }
        if(smallThanNum == 0){
            target[1] = -1;
        }else{
            target[1] = smallThanNum;
        }
        return target;
    }
    public static int bit1counts(int N){
        int count = 0;
        while(N != 0){
            int rightOne = N & (-N);
            count++;
            N ^= rightOne;
        }
        return count;
    }
    public int[] findClosedNumbers1(int num){
        int[] res = {-1,-1};
        int flag1 = 0;
        int flag2 = 0;
        int temp = num;
        int cur;
        int[] nums = new int[31];
        for (int i = 0; i < 31; i++) {
            nums[i] = (int)Math.pow(2,i);
        }
        for (int i = 0; i < 31 && (flag1 < 2 || flag2 < 2); i++) {
            cur = temp & 1;
            temp = temp >> 1;
            if(flag1 == 0 && cur == 0){
                flag1 = 1;
            }else if(flag1 == 1 && cur == 1){
                flag1 = 2;
                res[1] = num - nums[i] + nums[i - 1];
                int temp1 = res[1] & (nums[i - 1] - 1);
                res[1] = res[1] >> (i - 1);
                for (int j = 0; j < i-1; j++) {
                    res[1] = ((res[1] << 1) | (temp1 & 1));
                    temp1 = temp1 >> 1;
                }
            }
            if(flag2 == 0 && cur == 1){
                flag2 = 1;
            }else if(flag2 == 1 && cur == 0){
                flag2 = 2;
                res[0] = num - nums[i - 1] + nums[i];
                int temp1 = res[0] & (nums[i - 1] - 1);
                res[0] = res[0] >> (i - 1);
                for (int j = 0; j < i - 1; j++) {
                    res[0] = ((res[0] << 1) | (temp1 & 1));
                    temp1 = temp1 >> 1;
                }
            }
        }
        return res;
    }
    public int[] findClosedNumbers2(int num){
        int[] nums = new int[31];
        for (int i = 0; i < 31; i++) {
            nums[i] = (int)Math.pow(2,i);
        }
        int[] res = {-1, -1};
        int flag1 = 1, flag0 = 1;
        for (int i = 0; i < 30; i++) {
            if((nums[i] & num) == 0 && (nums[i + 1] & num) == 1 && flag1 == 0){
                res[1] = num - nums[i + 1] + nums[i];
                flag1 = 0;
                int k = -1, l = i;
                while(l > 0 && ((nums[l - 1] & num) == 0) && (nums[k + 1] & num) == 1){
                    res[1] = res[1] - nums[++k] + nums[--l];
                }
            }
            if((nums[i] & num) == 1 && (nums[i + 1] & num) == 0 && flag0 == 0){
                res[0] = num - nums[i] + nums[i + 1];
                flag0 = 0;
                int k = -1, l = i;
                while(l > 0 && (nums[l - 1] & num) == 1 && (nums[k + 1] & num) == 0){
                    res[0] = res[0] + nums[++k] - nums[--l];
                }
            }
        }
        return res;
    }
}
