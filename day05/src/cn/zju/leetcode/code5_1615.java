package cn.zju.leetcode;

import java.util.*;

public class code5_1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indeg = new int[n];
        for (int[] road : roads) {
            List<Integer> list1 = map.getOrDefault(road[0], new ArrayList<Integer>());
            list1.add(road[1]);
            map.put(road[0], list1);
            List<Integer> list2 = map.getOrDefault(road[1], new ArrayList<Integer>());
            list2.add(road[0]);
            map.put(road[1], list2);
            indeg[road[0]]++;
            indeg[road[1]]++;
        }
        int first = -1, second = -2;
        for (int i = 0; i < n; i++) {
            if(indeg[i] > first){
                second = first;
                first = indeg[i];
            } else if (indeg[i] > second) {
                second = indeg[i];
            }
        }
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(indeg[i] == first){
                firstList.add(i);
            }
            if(indeg[i] == second){
                secondList.add(i);
            }
        }
        if(firstList.size() == 1){
            int u = firstList.get(0);
            for (int v : secondList) {
                if(!map.get(u).contains(v)){
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if(firstList.size() * (firstList.size() - 1) / 2 > m){
                return first << 1;
            }
            for (int u : firstList) {
                for (int v : firstList) {
                    if(u != v && !map.get(u).contains(v)){
                        return first << 1;
                    }
                }
            }
            return (first << 1) - 1;
        }
    }
    public int maximalNetworkRank1(int n, int[][] roads) {
        int[][] map = new int[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            map[road[0]][road[1]]++;
            map[road[1]][road[0]]++;
            degree[road[0]]++;
            degree[road[1]]++;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = degree[i] + degree[j] - map[i][j];
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }
}
