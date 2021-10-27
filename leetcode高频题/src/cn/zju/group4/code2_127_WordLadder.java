package cn.zju.group4;

import java.util.*;
// 单词接龙1
public class code2_127_WordLadder {
    public int ladderLength1(String start, String to, List<String> wordList) {
        wordList.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(wordList);
        HashMap<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 1);
        Set<String> set = new HashSet<>();
        set.add(start);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if (next.equals(to)) {
                    return distance + 1;
                }
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }
        }
        return 0;
    }
    public HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    public ArrayList<String> getNext(String word, HashSet<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != cur) {
                    char tmp = chars[i];
                    chars[i] = cur;
                    if (dict.contains(String.valueOf(chars))) {
                        res.add(String.valueOf(chars));
                    }
                    chars[i] = tmp;
                }
            }
        }
        return res;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visit = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        for (int len = 2; !startSet.isEmpty(); len++) {
            Set<String> nextSet = new HashSet<>();
            for (String w : startSet) {
                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != w.charAt(j)) {
                            ch[j] = c;
                            String next = String.valueOf(ch);
                            if (endSet.contains(next)) {
                                return len;
                            }
                            if (dict.contains(next) && !visit.contains(next)) {
                                nextSet.add(next);
                                visit.add(next);
                            }
                        }
                    }
                }
            }
            startSet = (nextSet.size() < endSet.size()) ? nextSet : endSet;
            endSet = (startSet == nextSet) ? endSet : nextSet;
        }
        return 0;
    }
}
