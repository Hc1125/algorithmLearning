package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code2_1505 {
    public String minInteger(String num, int k){
        int n = num.length();
        Queue<Integer>[] pos = new Queue[10];
        for (int i = 0; i < 10; i++) {
            pos[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            pos[num.charAt(i) - '0'].offer(i + 1);
        }
        StringBuffer ans = new StringBuffer();
        BIT bit = new BIT(n);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if(!pos[j].isEmpty()){
                    int behind = bit.query(pos[j].peek() + 1, n);
                    int dist = pos[j].peek() + behind - i;
                    if(dist <= k){
                        bit.update(pos[j].poll());
                        ans.append(j);
                        k -= dist;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
