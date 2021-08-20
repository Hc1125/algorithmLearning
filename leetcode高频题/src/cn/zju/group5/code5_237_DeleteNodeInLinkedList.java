package cn.zju.group5;

public class code5_237_DeleteNodeInLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
