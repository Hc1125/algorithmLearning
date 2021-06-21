package cn.zju.leetcode;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class code11_1297 {
    public int maxFreq1(String s, int maxLetters, int minSize, int maxSize){
        int n = s.length();
        char[] chars = s.toCharArray();
        Map<String,Integer> map = new HashMap<String, Integer>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> exist = new HashSet<>();
            StringBuffer stringBuffer = new StringBuffer();
            for(int j = i; j < Math.min(n,i+maxSize);j++){
                exist.add(chars[j]);
                if(exist.size()>maxLetters){
                    break;
                }
                stringBuffer.append(chars[j]);
                if(j - i + 1 >= minSize){
                    map.put(stringBuffer.toString(),map.getOrDefault(stringBuffer.toString(),0)+1);
                    ans = Math.max(ans,map.get(stringBuffer.toString()));
                }

            }
        }
        return ans;
    }
    public int maxFreq2(String s, int maxLetters, int minSize, int maxSize){
        int n = s.length();
        Map<String,Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n - minSize + 1; i++) {
            String cur = s.substring(i,minSize+i);
            Set<Character> exsit = new HashSet<>();
            char[] chars = cur.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                exsit.add(chars[j]);
            }
            if(exsit.size() <= maxLetters){
                map.put(cur,map.getOrDefault(cur,0)+1);
                ans = Math.max(ans,map.get(cur));
            }
        }
        return ans;
    }

    /**
     * Rabin-Karp算法
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public static int maxFreq3(String s, int maxLetters, int minSize, int maxSize){
        int n = s.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int)(s.charAt(i) - 'a');
        }
        HashMap<Long,Integer> map = new HashMap<>();
        HashMap<Integer,Integer> exist = new HashMap<>();
        int a = 26;
        int letterNumber = 0;
        long modulus = (long)Math.pow(2,32);
        long hashValue = 0;
        long hashIndex = 1;
        for (int i = 0; i < minSize; i++) {
            hashValue = (hashValue * a + nums[i]) % modulus;
            hashIndex = (hashIndex * a) % modulus;
            exist.put(nums[i],exist.getOrDefault(nums[i],0) + 1);
            if(exist.get(nums[i]) == 1){
                letterNumber++;
            }
        }
        if(letterNumber <= maxLetters){
            map.put(hashValue,1);
        }
        int ans = 0;
        for (int start = 1; start < n - minSize + 1; start++) {
            hashValue = (hashValue * a - nums[start - 1] * hashIndex + nums[start + minSize -1]) % modulus;
            exist.put(nums[start-1],exist.get(nums[start-1]) - 1);
            if(exist.get(nums[start - 1]) == 0){
                letterNumber--;
            }
            exist.put(nums[start + minSize -1],exist.getOrDefault(nums[start + minSize -1],0)+1);
            if(exist.get(nums[start + minSize -1]) == 1){
                letterNumber++;
            }
            if(letterNumber <= maxLetters){
                map.put(hashValue,map.getOrDefault(hashValue,0)+1);
                ans = Math.max(ans,map.get(hashValue));
            }
        }
        return ans;
    }
}
