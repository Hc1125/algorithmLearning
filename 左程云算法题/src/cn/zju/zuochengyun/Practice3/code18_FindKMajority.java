package cn.zju.zuochengyun.Practice3;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class code18_FindKMajority {
    /**
     * 申请的额外空间为O（1）
     * 给定一个数组arr，如果有某个数出现次数超过数组长度的一半，打印这个数，如果没有不打印
     * 给定一个数组arr和整数k，arr长度为N，如果有某些数出现次数超过N/K，打印这些数，如果没有，不打印
     */

    public static void printHalfMajor(int[] arr) {
        int cand = 0;
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (times == 0) {
                cand = arr[i];
                times = 1;
            } else if (arr[i] == cand) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand) {
                times++;
            }
        }
        if (times > arr.length / 2) {
            System.out.println(cand);
        } else {
            System.out.println("no such number.");
        }
    }



    /**
     * 给定一个数组arr和整数k，arr长度为N，如果有某些数出现次数超过了N / K
     * 打印这些数，如果没有则不打印
     */

    public static void printKMajor(int[] arr, int K) {
        if (K < 2) {
            return;
        }
        HashMap<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == K - 1) {
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> entry : cands.entrySet()) {
            Integer key = entry.getKey();
            if (reals.get(key) > arr.length / K) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number.");
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1) {
                removeList.add(key);  // 在遍历过程中删除节点会扰乱迭代器，所以先存到链表里，再统一遍历删除
            }
            map.put(key, value - 1);
        }
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr, K);
    }
}
