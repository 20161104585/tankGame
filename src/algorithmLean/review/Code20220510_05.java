package algorithmLean.review;

/**
 * @Description: 两个有序链表合并
 * @Author: haole
 * @Date: 2022/6/29 18:00
 */
public class Code20220510_05 {
    public static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node mergeTwoSortedLinkedList(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 != null ? node1 : node2;
        }

        Node head = node1.value > node2.value ? node2 : node1;


        Node temp = head;
        Node cur1 = head.next;
        Node cur2 = node2 == head ? node1 : node2;

        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                temp.next = cur1;
                cur1 = cur1.next;
            } else {
                temp.next = cur2;
                cur2 = cur2.next;
            }
            temp = temp.next;
        }
        temp.next = cur1 != null ? cur1 : cur2;
        return head;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(7);
        node1.next.next.next.next = new Node(9);
//        while (node1 != null) {
//            System.out.print(node1.value + "->");
//            node1 = node1.next;
//        }
//        System.out.println();

        Node node2 = new Node(2);
        node2.next = new Node(4);
        node2.next.next = new Node(6);
        node2.next.next.next = new Node(8);
        node2.next.next.next.next = new Node(10);
//        while (node2 != null) {
//            System.out.print(node2.value + "->");
//        }
//        System.out.println();

        Node ans = mergeTwoSortedLinkedList(node1, node2);
        while (ans != null) {
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
    }
}