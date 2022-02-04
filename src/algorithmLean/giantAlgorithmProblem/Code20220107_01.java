package algorithmLean.giantAlgorithmProblem;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 一些基础的数据结构
 * @Author: haole
 * @Date: 2022/1/7 10:26
 */
public class Code20220107_01 {
    public static void main(String[] args) {
//        int len = 500;
//        int value = 100;
//        int testTimes = 100000;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTimes; i++) {
//            //获取随机单链表
//            Node node1 = generateRandomLinkedlist(len, value);
//            //记录单链表每个位置值。
//            ArrayList<Integer> list = getNodesValue(node1);
//            node1 = reverseLinkedList(node1);
//            if (!checkLinkedlistReverse(node1, list)) {
//                System.out.println("出错了！");
//            }
//        }
//        System.out.println("测试结束");


        MyQuery2 query2 = new MyQuery2();
        query2.push(1);
        query2.push(2);
        query2.push(3);
        query2.push(4);
        query2.push(5);

        for (int i = 0; i < 5; i++) {
            System.out.println(query2.pop());
        }

    }

    /**
     * ----------------------------------------------------------------------------------------------------------
     * 第一章节：单链表和双链表
     */
    private static boolean checkLinkedlistReverse(Node node1, ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != node1.value) {
                return false;
            }
            node1 = node1.next;
        }
        return true;
    }

    private static ArrayList<Integer> getNodesValue(Node node1) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (node1 != null) {
            arrayList.add((int) node1.value);
            node1 = node1.next;
        }
        return arrayList;
    }

    /**
     * 单链表反转
     * a -> b -> c -> null
     * c -> b -> a -> null
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
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
     * 双链表反转
     * a -> b -> c -> null
     * null <-   <-   <-   <-
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
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

    /**
     * 使用容器解决，对数器使用。
     *
     * @param head
     * @return
     */
    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        nodes.get(0).next = null;
        for (int i = 1; i < (nodes.size() - 1); i++) {
            nodes.get(i).next = nodes.get(i - 1);
        }
        return nodes.get(nodes.size() - 1);
    }

    /**
     * 根据单链表长度、取值范围，随机生成一个单链表，并返回头节点。
     */
    public static Node generateRandomLinkedlist(int len, int value) {
        int size = (int) (Math.random() * len + 1);
        if (size == 0) {
            return null;
        }

        Node head = new Node((int) (Math.random() * value + 1));
        Node pre = head;
        size--;
        while (size > 0) {
            Node node = new Node((int) (Math.random() * value + 1));
            pre.next = node;
            pre = node;
            size--;
        }
        return head;
    }

    /**
     * 单链表，将给定的值的节点删除
     */
    public static Node removeValue(Node head, int value) {
        //先处理头节点可能删除的情况
        while (head != null) {
            if ((int) head.value != value) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if ((int) cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * ----------------------------------------------------------------------------------------------------------
     * 第二章节：栈和队列
     * 基础概念：
     *         1）栈：数据先进后出，犹如弹夹
     *         2）队列：数据先进先出，好似排队
     */

    /**
     * 练习1：使用双链表实现栈
     */

    /**
     * 练习2：使用数组实现栈
     */

    /**
     * 练习3：使用双链表实现队列
     */

    /**
     * 练习4：使用数组实现队列
     */
    public class MyQueryArray {
        private int[] arr; //固定数组
        private int pushi; //end 在哪添加
        private int polli; //begin 在哪弹出
        private int size;  //一共还有几个数
        private final int limit; //数组长度

        public MyQueryArray(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (this.size == limit) {
                throw new RuntimeException("队列满了，不能加了！");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);

        }

        public int pull() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能弹出了");
            }
            size--;
            int resValue = arr[polli];
            polli = nextIndex(polli);
            return resValue;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

    /**
     * 练习5：使用栈实现push、pop，另外增加一个获取栈中最小值的功能
     */
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int value) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(value);
            } else if (value <= this.getMin()) {
                this.stackMin.push(value);
            }
            this.stackData.push(value);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("栈空了，不能再取了");
            }
            int value = this.stackData.pop();
            if (value == this.getMin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("栈空了");
            }
            return this.stackMin.peek();
        }

    }

    /**
     * 练习6：如何用栈结构实现队列结构
     */
    public static class MyQuery2 {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public MyQuery2() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        private void pushToPop() {
            if (this.stackPop.isEmpty()) {
                while (!this.stackPush.isEmpty()) {
                    this.stackPop.push(this.stackPush.pop());
                }
            }
        }

        public void push(int value) {
            this.stackPush.push(value);
            pushToPop();
        }

        public int pop() {
            if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
                throw new RuntimeException("栈空了！");
            }
            pushToPop();
            return this.stackPop.pop();
        }

        public int peek() {
            if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
                throw new RuntimeException("栈空了！");
            }
            pushToPop();
            return this.stackPop.peek();
        }

        public boolean isEmpty() {
            return this.stackPop.isEmpty() && this.stackPush.isEmpty();
        }
    }

    /**
     * 练习7：如何用队列结构实现栈结构
     */
    public static class MyStack3 {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public MyStack3() {
            this.data = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int value) {
            data.add(value);
        }

        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("队列空了！");
            }

            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        public int peek() {
            if (data.isEmpty()) {
                throw new RuntimeException("队列空了！");
            }

            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> temp = data;
            data = help;
            help = temp;
        }
    }

    /**
     * ----------------------------------------------------------------------------------------------------------
     * 第三章节：递归
     * 基础概念：
     * 1）在L..R范围上求最大值
     * 2）mid = L + ((R -L) >> 1)
     * 3）对于新手来说，把调用的过程画出结构图是必须的，有利于分析递归
     * 4）Master公式，用于分析递归函数的复杂度
     *      形如：T(N) = a * T(N/b) + O(N^d) (其中a、b、d都是常数)
     *      a为子过程发生几次，b为子过程数据量，O(N^d)为除递归其他的时间复杂度
     *      ①当d<logb a时，时间复杂度为O(n^(logb a))
     *      ②当d=logb a时，时间复杂度为O((n^d)*logn)
     *      ③当d>logb a时，时间复杂度为O(n^d)
     *      注意：适用范围为子过程规模相等的情况，否则不适用。
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 从求数组最大值认识递归
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);

        int lMax = process(arr, L, mid);
        int rMax = process(arr, mid + 1, R);
        return Math.max(lMax, rMax);
    }

    /**
     * ----------------------------------------------------------------------------------------------------------
     * 第四章节：哈希表和有序表
     * 基础概念：
     * 1）哈希表（HASH）的增删改查，无关数据量大小，时间复杂度都是常数时间
     * 2）TreeMap有序表：接口名
     *      红黑树、avl树、sb树、跳表都可以实现有序表，所有的实现出来都是O(logN)
     */

}
