package cn.zju.zuochengyun.LinkedList;


public class code3_SmallerEqualBigger {
    public static Node listPartition1(Node head, int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for(i = 1; i < nodeArr.length; i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }
    public static void arrPartition(Node[] nodeArr, int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while(index != big){
            if(nodeArr[index].value < pivot){
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot){
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }
    public static void swap(Node[] nodeArr, int a, int b){
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }
    public static Node listPartition2(Node head, int pivot){
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            } else{
                if(mH == null){
                    mH = head;
                    mT = head;
                }else{
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if(sT != null){                // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT;  //下一步，谁去连大于区域的头，谁就变成eT
        }

        // eT 尽量不为空的尾巴节点
        if(eT != null){               // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }
}
