package algorithmLean;

/**
 * @Description: 链表问题
 * @Author: haole
 * @Date: 2021/7/27 9:20
 */
public class Code04 {
    /**
     * 单链表
     */
    public static class Node<V> {
        public V value;
        public Node next;

        public Node(V data) {
            value = data;
        }
    }

    /**
     * 单链表实现队列
     *
     * @param <V>
     */
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue(V data) {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V value) {
            Node<V> cur = new Node<V>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;

        }

        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    /**
     * 单链表实现栈
     *
     * @param <V>
     */
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            Node<V> cur = new Node<V>(value);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }
    }

    /**
     * 双链表
     */
    public static class DoubleNode<V> {
        public V value;
        public DoubleNode<V> last;
        public DoubleNode<V> next;

        public DoubleNode(V v) {
            value = v;
            last = null;
            next = null;
        }
    }

    /**
     * 双链表实现双端队列
     *
     * @param <V>
     */
    public static class MyDeque<V> {
        private DoubleNode<V> head;
        private DoubleNode<V> tail;
        private int size;

        public MyDeque(V data) {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V value) {
            DoubleNode<V> cur = new DoubleNode<V>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        public void pushTail(V value) {
            DoubleNode<V> cur = new DoubleNode<V>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        public V pollHead() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = head.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            return ans;
        }

        public V pollTail() {
            V ans = null;
            if (tail == null) {
                return ans;
            }
            size--;
            ans = tail.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            return ans;
        }
    }

    /**
     * 单链表逆序方法
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        //逆序后头节点
        Node pre = null;
        //下一节点
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 双链表逆序
     *
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        //逆序后头节点
        DoubleNode pre = null;
        //下一节点
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        Node n2 = reverseLinkedList(n1);
        while (n2 != null) {
            System.out.print(n2.value + " ");
            n2 = n2.next;
        }
        System.out.println();

    }
}
