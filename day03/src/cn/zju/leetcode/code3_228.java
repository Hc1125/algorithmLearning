package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class code3_228 {
    public List<String> summaryRanges(int[] nums){
        int n = nums.length;
        List<String> ans = new LinkedList<String>();
        if(n == 0){
            return ans;
        }
        int min = Integer.MIN_VALUE;
        int begin = min;
        int end = min;
        for (int i = 0; i < n; i++) {
            if(end != nums[i]){
                if(end != min){
                    if(end - 1 == begin){
                        String s = ""+begin;
                        ans.add(s);
                    }else{
                        String s = begin + "->" + (end - 1);
                        ans.add(s);
                    }
                }
                begin = nums[i];
                end = begin;
            }
            end++;
        }
        if(end - 1 == begin){
            String s = ""+begin;
            ans.add(s);
        }else{
            String s = begin + "->" + (end - 1);
            ans.add(s);
        }
        return ans;
    }
    public List<String> summaryRanges1(int[] nums){
        List<String> ret = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while(i < n){
            int low = i;
            i++;
            while(i < n && nums[i-1] + 1 == nums[i]){
                i++;
            }
            int high = i - 1;
            StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));
            if(low < high){
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }
}
