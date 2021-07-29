package algorithmLean;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 比较器、优先级队列、二叉树
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * @Author: haole
 * @Date: 2021/7/28 10:57
 */
public class Code06_MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int data) {
            val = data;
        }
    }

    public static class NodeComparator implements Comparator<ListNode> {

        //如果返回负数，认为第一个参数应该排在前面
        //如果返回正数，认为第二个参数应该排在前面
        //如果返回0，认为谁排在前面无所谓
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new NodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }
}
