package algorithmLean;

/**
 * @Description: K个一组反转链表
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * 描述：给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * @Author: haole
 * @Date: 2021/7/27 13:46
 */
public class Code04_ReverseNodesInKGroup {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     * K个一组反转链表
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;

    }

    /**
     * 返回指定长度K个后的节点
     *
     * @param start
     * @param k
     * @return
     */
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    /**
     * 给定链表组内逆序
     *
     * @param start
     * @param end
     */
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        //逆序后头节点
        ListNode pre = null;
        ListNode cur = start;
        //下一节点
        ListNode next = null;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
