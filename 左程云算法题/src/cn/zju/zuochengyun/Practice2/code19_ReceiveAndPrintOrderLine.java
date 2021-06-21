package cn.zju.zuochengyun.Practice2;

import java.util.HashMap;

/**
 * 一种消息接受并打印的结构设计
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序吐出。如果上次打印的数为i，
 * 那么当i+1出现时，请打印i+1及其之后接受过的并且连续的所有数，直到1~N全部接受并打印完，
 * 请设计这种接受并打印的结构
 * 初始时默认i==0
 */
public class code19_ReceiveAndPrintOrderLine {
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<Integer, Node>();
            tailMap = new HashMap<Integer, Node>();
            waitPoint = 1;
        }

        // 消息的编号，info消息的内容，消息一定从1开始
        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            // num ~ num
            headMap.put(num, cur);
            tailMap.put(num, cur);
            // 建立了num~num这个连续区间的头和尾
            // 查询有没有某个连续区间以num-1结尾
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            // 查询有没有某个连续区间以num+1开头
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                tailMap.remove(num);
                headMap.remove(num + 1);
            }
            if (num == waitPoint) {
                print();
            }
        }

        private void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.println(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }
    }
}
