package algorithmLean;

import algorithmLean.review.Code20220510_05;

/**
 * @Description: 两个链表合并
 * @Author: haole
 * @Date: 2021/7/27 16:09
 */
public class Code04_MergeTwoSortedLinkedList {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int data) {
            val = data;
        }
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            // 此出必须让指针下滑
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(5);
        node1.next.next.next = new ListNode(7);
        node1.next.next.next.next = new ListNode(9);
//        while (node1 != null) {
//            System.out.print(node1.value + "->");
//            node1 = node1.next;
//        }
//        System.out.println();

        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(4);
        node2.next.next = new ListNode(6);
        node2.next.next.next = new ListNode(8);
        node2.next.next.next.next = new ListNode(10);
//        while (node2 != null) {
//            System.out.print(node2.value + "->");
//        }
//        System.out.println();

        ListNode ans = mergeTwoLists(node1, node2);
        while (ans != null) {
            System.out.print(ans.val + "->");
            ans = ans.next;
        }
    }
}
