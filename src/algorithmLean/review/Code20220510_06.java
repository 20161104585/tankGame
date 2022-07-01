package algorithmLean.review;

/**
 * @Description: K个一组链表逆序
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * 描述：给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * @Author: haole
 * @Date: 2022/7/1 11:08
 */
public class Code20220510_06 {

    public static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverseKGroup(Node head, int k) {
        Node start = head;
        Node end = getKGroupEnd(start, k);
        if (end == null) {
            return start;
        }
        // 反转后end节点为新的头节点
        head = end;
        // 反转节点
        reverse(start, end);
        // 上一组的结尾节点
        Node lastEnd = start;
        while (lastEnd != null) {
            // 新一组开始节点，为上一组结尾节点的next节点
            start = lastEnd.next;
            // 继续数k个节点
            end = getKGroupEnd(start,k);
            if(end == null){
                return head;
            }
            // 反转节点
            reverse(start, end);
            // 上一组的end指针，应该指向这一组反转后的头指针（即end）
            lastEnd.next = end;
            // lastEnd 应该指向这一组反转后的尾指针（即start）
            lastEnd = start;
        }

        return head;
    }

    /**
     * 返回从传入节点开始，K个后的节点
     *
     * @param start
     * @param k
     * @return
     */
    private static Node getKGroupEnd(Node start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    /**
     * 反转给定节点
     *
     * @param start
     * @param end
     */
    private static void reverse(Node start, Node end) {
        // 先将end移动到下一节点，
        end = end.next;

        // 反转单链表
        Node cur = start;
        Node next = null;
        Node pre = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 最后将指向null的节点挂到end节点上去，完场反转，
        start.next = end;
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(7);
        node1.next.next.next.next = new Node(9);
        Node ans = reverseKGroup(node1, 5);
        while (ans != null) {
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
    }

}
