package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code14_638 {
    static int ans = Integer.MAX_VALUE;
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int state = 0;
        for (int i = 0; i < needs.size(); i++) {
            state |= needs.get(i) << (4 * i);
        }
        List<List<Integer>> gifts = new ArrayList<>();
        circle : for (int i = 0; i < special.size(); i++) {
            for (int j = 0; j < special.get(i).size() - 1; j++) {
                if (special.get(i).get(j) > needs.get(j)) {
                    continue circle;
                }
            }
            gifts.add(special.get(i));
        }
        process(price, gifts, state, 0, 0);
        return ans;
    }
    public static void process(List<Integer> price, List<List<Integer>> gifts, int state, int index, int cost) {
        if (state == 0) {
            ans = Math.min(ans, cost);
            return;
        }
        if (index == gifts.size()) {
            int tmp = state;
            for (int i = 0; i < price.size(); i++) {
                cost += (state & 15) * price.get(i);
                state >>= 4;
            }
            ans = Math.min(ans, cost);
            return;
        }
        process(price, gifts, state, index + 1, cost);
        List<Integer> gift = gifts.get(index);
        int k = 1;
        while (true) {
            int newState = 0;
            for (int i = 0; i < gift.size() - 1; i++) {
                int num = (state >> (4 * i)) & 15;
                if (gift.get(i) * k > num) {
                    return ;
                }
                int left = num - gift.get(i) * k;
                newState |= left << (4 * i);
            }
            process(price, gifts, newState, index + 1, cost + k * gift.get(gift.size() - 1));
            k++;
        }
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>();
        price.add(3);
        price.add(4);
        List<List<Integer>> special = new ArrayList<>();
        special.add(new ArrayList<>());
        special.add(new ArrayList<>());
        special.get(0).add(1);
        special.get(0).add(2);
        special.get(0).add(3);
        special.get(1).add(1);
        special.get(1).add(2);
        special.get(1).add(5);
        List<Integer> need = new ArrayList<>();
        need.add(2);
        need.add(2);
        System.out.println(shoppingOffers(price, special, need));
    }
}
