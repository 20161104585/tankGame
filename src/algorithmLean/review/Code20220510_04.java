package algorithmLean.review;

/**
 * @Description: 两个链表相加
 * @Author: haole
 * @Date: 2022/6/29 17:38
 */
public class Code20220510_04 {

    public static class Node {
        private Node next;
        private int value;

        public Node(int val) {
            this.value = val;
        }
    }


    public static Node addTwoNumbers(Node node1, Node node2) {
        // 先分别找出长短链表
        int len1 = getNodeLength(node1);
        int len2 = getNodeLength(node2);

        Node l = len1 > len2 ? node1 : node2;
        Node s = l == node1 ? node2 : node1;


        Node curL = l;
        Node curS = s;
        // 定义跟踪节点，最后需要挂进位信息
        Node last = curL;
        // 定义进位信息
        int carray = 0;
        int value = 0;
        // 先遍历短链表
        while (curS != null) {
            // 当前值为长链表 + 短链表 + 进位值
            value = curS.value + curL.value + carray;
            carray = value / 10;
            curL.value = (value % 10);
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        // 再遍历长链表
        while (curL != null) {
            value = curL.value + carray;
            carray = value / 10;
            curL.value = value % 10;
            last = curL;
            curL = curL.next;
        }
        // 最后处理进位信息
        while (carray != 0) {
            last.next = new Node(1);
        }
        // 返回长链表头节点
        return l;
    }

    private static int getNodeLength(Node head) {
        int ans = 0;
        while (head != null) {
            ans++;
            head = head.next;
        }
        return ans;
    }
}
