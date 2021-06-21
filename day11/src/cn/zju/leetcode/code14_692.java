package cn.zju.leetcode;

import java.util.*;

public class code14_692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Map.Entry<String, Integer>[] entrys = new Map.Entry[map.size()];
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entrys[index++] = entry;
        }
        List<String> ans = new ArrayList<>();
        int target = bfprt(entrys, 0, entrys.length - 1, entrys.length - k);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= target) {
                ans.add(entry.getKey());
            }
        }
        Collections.sort(ans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o2) == map.get(o1) ? o1.compareTo(o2) : map.get(o2) - map.get(o1);
            }
        });
        while (ans.size() > k) {
            ans.remove(ans.size() - 1);
        }
        return ans;
    }
    public int bfprt(Map.Entry<String, Integer>[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L].getValue();
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index].getValue();
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }
    public int medianOfMedians(Map.Entry<String, Integer>[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        Map.Entry<String, Integer>[] mArr = new Map.Entry[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public Map.Entry<String, Integer> getMedian(Map.Entry<String, Integer>[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public void insertionSort(Map.Entry<String, Integer>[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j].getValue() > arr[j + 1].getValue(); j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    public int[] partition(Map.Entry<String, Integer>[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur].getValue() < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur].getValue() > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less +1, more - 1};
    }
    public void swap(Map.Entry<String, Integer>[] arr, int i, int j) {
        Map.Entry<String, Integer> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> ans = new LinkedList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}
