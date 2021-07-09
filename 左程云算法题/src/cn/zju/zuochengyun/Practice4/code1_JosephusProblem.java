package cn.zju.zuochengyun.Practice4;

import cn.zju.zuochengyun.Node;

/**
 * 约瑟夫环问题：
 * N个人站成一个环形链表，从开头出发数数，从1数到K，数到K的人死去
 * 然后继续开始从1数到K，问最后活下来的人在原始编号中是多少
 *
 * 升级版K从一个数组里数，每死一个人就取下个数字
 * 把递归中的m改成数组的对应数字
 */
public class code1_JosephusProblem {
    public static Node josephusKill1(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node last = head;
        while (last != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node cur = head.next;
        int size = 1;
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        int live = getLive(size, m);
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    // 现在一共有i个节点，数到m就杀死节点，最终会活下来的节点，请返回它在有i个节点时候的编号
    // 旧
    // getLive(N, m)
    // i 为当前链表长度
    // 新 = (旧 + m - 1) % i + 1
    public static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        // getLive(i - 1, m) 长度为 i - 1的时候，活下来的编号
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }
}
