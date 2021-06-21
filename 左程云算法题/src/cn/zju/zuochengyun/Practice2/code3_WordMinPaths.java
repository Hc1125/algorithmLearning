package cn.zju.zuochengyun.Practice2;



import java.util.*;

public class code3_WordMinPaths {
    /**
     * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to，list中没有重复字符串
     * 所有的字符串都是小写的
     * 规定：start每次只能改变一个字符，最终的目标是彻底改变成to，但是每次编程的新字符串必须在list中存在
     * 请返回所有最短的变换路径
     */
    public static List<List<String>> findMinPaths(String start, String end, List<String> list) {
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        HashMap<String, Integer> distances = getDistances(start, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, nexts, distances, pathList, res);
        return res;
    }

    public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        Set<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i) ,dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] =tmp;
                }
            }
        }
        return res;
    }

    public static HashMap<String, Integer> getDistances(String start,
            HashMap<String, ArrayList<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    distances.put(next, distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    /**
     *
     * @param cur 出发
     * @param to  目的地
     * @param nexts 邻居表
     * @param distances 最短距离表
     * @param path      沿途走过的路径
     * @param res       答案往res里放，收集所有的最短路径
     */
    private static void getShortestPaths(String cur, String to,
             HashMap<String, ArrayList<String>> nexts,
             HashMap<String, Integer> distances, LinkedList<String> path, List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<String>(path));
        } else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }
}
