package cn.zju.zuochengyun.Practice1;


import java.util.HashMap;

/**
 * 请实现如下结构︰TopRecord{
 * public TopRecord(int K)∶构造时事先指定好K的大小，构造后就固定不变了
 * public void add(String str):向该结构中加入一个字符串，可以重复加入
 * public List<String> top()∶返回之前加入的所有字符串中，词频最大的K个
 * 要求︰
 * add方法,复杂度O(log K);top方法，复杂度O(K)
 */
public class code13_TopKTimesRealTime {
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopKRecord {
        private Node[] heap;
        private int heapSize;
        private HashMap<String, Node> strNodeMap;
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }
        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }

            // 词频表修改完毕
            if (preIndex == -1) {
                if (heapSize == heap.length) {
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, heapSize);
                    }
                } else {
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else {
                heapify(preIndex, heapSize);
            }
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i < heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.println("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }
        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) >> 1;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = 2 * index + 1;
                r = 2 * index + 2;
            }
        }
        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }
    }


}
