package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code12_1593____________ {
    int res = 0;
    Set<String> set = new HashSet<>();
    public int maxUniqueSplit(String s){
        backstracking(s,0);
        return res;
    }
    private void backstracking(String s, int begin){
        if(set.size() + s.length() - begin <= res){
            return;
        }
        if(begin == s.length()){
            if(set.size() > res){
                res = set.size();
            }
        }
        for(int i = begin; i < s.length(); i++){
            String substr = s.substring(begin, i + 1);
            if(!set.contains(substr)){
                set.add(substr);
                backstracking(s,i + 1);
                set.remove(substr);
            }
        }
    }
}
