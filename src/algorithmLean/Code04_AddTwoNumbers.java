package algorithmLean;

/**
 * @Description: 两个链表相加
 * @Author: haole
 * @Date: 2021/7/27 15:02
 */
public class Code04_AddTwoNumbers {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int data) {
            val = data;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);

        //长链表
        ListNode l = len1 > len2 ? head1 : head2;
        //短链表
        ListNode s = l == head1 ? head2 : head1;

        ListNode curL = l;
        ListNode curS = s;

        //跟踪指针
        ListNode last = curL;
        //进位值
        int carry = 0;
        //当前位值
        int curNum = 0;
        //第一阶段，短链表不为空循环
        while (curS != null) {
            curNum = curL.val + curS.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }

        //第二阶段，长链表不为空循环
        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }

        //第三阶段，处理进位信息
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return l;
    }

    /**
     * 返回链表长度
     *
     * @param head
     * @return
     */
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
