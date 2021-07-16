package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class code20_77 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
       List<Integer> list = new LinkedList<>();
       process(1, n, list, k);
       return ans;
    }
    public void process(int begin, int n, List<Integer> list, int k) {
        if (list.size() + n - begin + 1 < k) {
            return;
        }
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        process(begin + 1, n, list, k);
        list.add(begin);
        process(begin + 1, n, list, k);
        list.remove(list.size() - 1);
    }
}
