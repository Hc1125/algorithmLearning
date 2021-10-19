package cn.zju.leetcode5;


import java.util.HashMap;
import java.util.Map;

public class code10_30_RandomSet {
    class RandomizedSet {
        Map<Integer, Integer> keyIndexMap;
        Map<Integer, Integer> indexKeyMap;
        int n;
        public RandomizedSet() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            n = 0;
        }

        public boolean insert(int val) {
            if (!keyIndexMap.containsKey(val)) {
                keyIndexMap.put(val, n);
                indexKeyMap.put(n++, val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (keyIndexMap.containsKey(val)) {
                int lastKey = indexKeyMap.get(--n);
                int deleteIndex = keyIndexMap.get(val);
                keyIndexMap.put(lastKey, deleteIndex);
                indexKeyMap.put(deleteIndex, lastKey);
                keyIndexMap.remove(val);
                indexKeyMap.remove(n);
                return true;
            }
            return false;
        }


        public int getRandom() {
            int randomKey = (int) (Math.random() * n);
            return indexKeyMap.get(randomKey);
        }
    }

}
