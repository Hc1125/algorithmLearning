package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code9_1104 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> list = new ArrayList<>();
        int temp = label, index = 0;
        while (temp > 1) {
            temp /= 2;
            index++;
        }
        if (index % 2 == 1) {
            label = (int)(1.5 * Math.pow(2, index + 1)) - 1 - label;
        }
        while (label > 0) {
            list.add(0, label);
            label /= 2;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) {
                list.set(i, (int)(1.5 * Math.pow(2, i + 1)) - 1 - list.get(i));
            }
        }
        return list;
    }
}
