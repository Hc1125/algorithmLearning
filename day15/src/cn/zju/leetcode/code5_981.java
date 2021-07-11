package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class code5_981 {
    class TimeMap {
        HashMap<String, TreeMap<Integer, String>> timeMap;
        public TimeMap() {
            timeMap = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            timeMap.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!timeMap.containsKey(key)) {
                return "";
            } else {
                Integer time = timeMap.get(key).floorKey(timestamp);
                if (time == null) {
                    return "";
                }
                return timeMap.get(key).get(time);
            }
        }
    }
}
