package cn.zju.zuochengyun.ACAutomaton;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1)头节点fail指针指空
 * 2)第一层节点fail指针指头节点
 * 3)普遍位置fail指针，先找父节点的fail指针指向节点是否有相同的路，有就直接指向
 * 无则继续查找fail指针指向的fail指针是否有相同的路，重复这个过程，直到fail指向null
 * 则普遍位置fail指针指向头节点
 */
public class code1_AC1 {
    public static class Node {
        public int end; // 有多少个字符串以该节点结尾
        public Node fail;
        public Node[] nexts;

        public Node() {
            end = 0;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        // 你有多少个匹配串，就调用多少次insert
        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            cur.end++;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll(); // 父
                for (int i = 0; i < 26; i++) { // 下级所有的路
                    if (cur.nexts[i] != null) { // 该路下有子节点
                        cur.nexts[i].fail = root; // 初始时先设置一个值
                        cfail = cur.fail;
                        while (cfail != null) { // cur不是头节点
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public int containNum(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            int ans = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end == -1) {
                        break;
                    }
                    { // 不同的需求，在这一段{ }之间修改
                        ans += follow.end;
                        follow.end = -1;
                    } // 不同的需求，在这一段{ }之间修改
                    follow = follow.fail;
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhehe"));
    }
}
