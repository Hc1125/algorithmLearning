package cn.zju.leetcode;

import java.util.*;

public class code7_1418 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<Integer, Map<String, Integer>> indexMap = new TreeMap<>();
        Set<String> itemSet = new TreeSet<>();
        for (List<String> order : orders) {
            int index = Integer.parseInt(order.get(1));
            Map<String, Integer> map = indexMap.getOrDefault(index, new HashMap<String, Integer>());
            String item = order.get(2);
            itemSet.add(item);
            map.put(item, map.getOrDefault(item, 0) + 1);
            indexMap.put(index, map);
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> itemList = new ArrayList<>();
        itemList.add("Table");
        for (String s : itemSet) {
            itemList.add(s);
        }
        ans.add(itemList);
        for (Map.Entry<Integer, Map<String, Integer>> entry : indexMap.entrySet()) {
            List<String> tableList = new ArrayList<>();
            tableList.add(String.valueOf(entry.getKey()));
            Map<String, Integer> map = entry.getValue();
            for (int i = 1; i < itemList.size(); i++) {
                String item = itemList.get(i);
                if (map.containsKey(item)) {
                    tableList.add(String.valueOf(map.get(item)));
                } else {
                    tableList.add("0");
                }
            }
            ans.add(tableList);
        }
        return ans;
    }
}
