package cn.zju.leetcode;

import java.util.*;

public class code12_1044 {
    /**
     * 暴力解法
     * @param s
     * @return
     */
    public String longestDupSubstring(String s){
        int n = s.length();
        int len = n - 1;
        while(len > 0){
            Set<String> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if(i + len > n){
                    break;
                }
                String cur = s.substring(i,i+len);
                if(set.contains(cur)){
                    return cur;
                }else {
                    set.add(cur);
                }
            }
            len--;
        }
        return "";
    }

    /**
     * 二分法查找
     * @param s
     * @return
     */
    public String longestDupSubstring1(String s){
        int n = s.length();
        int left = 1;
        int right = n;
        String ans = "";
        while(left < right){
            Set<String> set = new HashSet<>();
            int mid = (left + right) / 2;
            boolean success = false;
            for (int i = 0; i < n; i++) {
                if(i + mid > n){
                    break;
                }
                String cur = s.substring(i,i+mid);
                if(set.contains(cur)){
                    ans = cur;
                    success = true;
                    break;
                }else {
                    set.add(cur);
                }
            }
            if(success){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return ans;
    }

    /**
     * 二分查找 + Rabin-Krap字符串编码
     * @param s
     * @return
     */
    public String longestDupSubstring2(String s){
        int n = s.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int)(s.charAt(i) - 'a');
        }
        int a = 26;
        long modulus = (long)Math.pow(2,32);
        int left = 1, right = n;
        int L;
        while(left != right){
            L = left + (right - left) / 2;
            if(search(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L;
        }
        int start = search(left - 1, a, modulus, n, nums);
        return start != -1 ? s.substring(start, start + left - 1) : "";
    }
    public int search(int L, int a, long modulus, int n, int[] nums){
        long h = 0;
        long aL = 1;
        for (int i = 0; i < L; i++) {
            h = (h * a + nums[i]) % modulus;
            aL = (aL * a) % modulus;
        }
        HashMap<Long, List<Integer>> seen = new HashMap<>();
        seen.put(h,Arrays.asList(0,L-1));
        for(int start = 1; start < n - L + 1; start++){
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L -1]) % modulus;
            if(seen.containsKey(h)){
                if(check(nums,Arrays.asList(start,start+L-1),seen.get(h))){
                    return start;
                }
            }
            seen.put(h,Arrays.asList(start,start+L-1));
        }
        return -1;
    }
    public boolean check(int[] nums,List<Integer> list1, List<Integer> list2){
        int begin1 = list1.get(0);
        int end1 = list1.get(1);
        int begin2 = list2.get(0);
        int end2 = list2.get(1);
        while(begin1 <= end1 && begin2 <= end2){
            if(nums[begin1] != nums[begin2]){
                return false;
            }
            begin1++;
            begin2++;
        }
        return true;
    }
}
