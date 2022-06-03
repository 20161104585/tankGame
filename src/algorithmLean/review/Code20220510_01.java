package algorithmLean.review;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 第三节：单双链表、栈和队列、递归和Master公式、哈希表和有序表的使用和性能
 * 题目：
 * 1.反转单链表、反转双链表
 * <p>
 * 2.在链表中删除指定值的所有节点
 * <p>
 * 3.用双链表实现栈和队列
 * <p>
 * 4.用环形数组实现栈和队列
 * <p>
 * 5.实现有getMin功能的栈
 * <p>
 * 6.两个栈实现队列
 * <p>
 * 7.两个队列实现栈
 * <p>
 * 8.用递归行为得到数组中的最大值，并用master公式来估计时间复杂度
 * <p>
 * 9.哈希表和有序表使用的code展示
 * @Author: haole
 * @Date: 2022/5/10 15:13
 */
public class Code20220510_01 {
    // 不可见字符
    private static final String SPLIT = String.valueOf((char) 2);

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 10; i++) {
            stringBuffer.append(i + SPLIT + "zhangsan" + "\r\n");
        }
        stringBuffer.insert(0, 0 + SPLIT + "zhangsan" + "\r\n");

        System.out.println(stringBuffer);

        MyQueueImplTwoStack test = new MyQueueImplTwoStack();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());

    }

    /**
     * 1.反转单链表
     *
     * @param head
     * @return
     */
    private static Node reversalList(Node head) {
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

    public class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 反转双链表
     *
     * @param head
     * @return
     */
    private static DoubleNode reversalDblNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        DoubleNode last = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 2.在链表中删除指定值的所有节点
     */
    private static Node removeNode(Node head, int value) {
        if (head == null) {
            return head;
        }
        Node resNode = null;
        while (head != null && head.value == value) {
            head = head.next;
        }

        resNode = head;
        while (head != null && head.next != null) {
            if (head.next.value == value) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return resNode;
    }

    /**
     * 3.用双链表实现栈和队列
     * <p>
     * 3.1实现队列：逻辑概念就是先进先出
     * 首先需要有一个双链表的头指针H、一个尾指针T，一开始H、T都指向NULL；
     * 当进入第一个数据时，封装其next指针指向null,last指针也指向null,H、T都指向当前节点；
     * 当继续进入数据，封装其next指针指向null,last指针指向第一个节点，第一个节点的next指针指向当前节点，H指针不动，T指针指向当前节点
     * 当需要弹出数据，获取头指针指向的节点当作返回值，在链表中释放当前节点，H指向原来节点下一个
     * 3.2实现栈结构：逻辑概念就是先进后出，像弹夹
     * 首先需要有一个双链表的头指针H，一开始指向null,
     * 当进入第一个数据时，封装其next指针指向null,last指针也指向null,H指向当前节点；
     * 当继续进入数据，封装其next指针指向null,last指针指向第一个节点，第一个节点的next指针指向当前节点，H指向当前节点
     * 当需要弹出数据，获取头指针指向的节点当作返回值，在链表中释放当前节点，H指向原来节点下一个
     */
    public static class Suan03Queue01 {
        /*
         * 用链表实现队列和栈
         * 思路：首先明确单链表和双链表都可以实现队列和栈，我们这里用双链表，因为更锻炼思维能力
         *
         * */
        public static class Node<T> {
            T value;
            Node<T> pre;
            Node<T> next;

            Node(T value) {
                this.value = value;
            }
        }

        /*
         * 首先明确SQMock类的作用，这就是链表和队列和栈的中间桥梁
         * 因为队列的特点先进先出，所以我们就应该想到对队列的入队和出队就是链表的尾插和头出，或者头插和尾出，二者都可以看你怎么想
         * 我们这里选尾插和头出（明确头和尾只是自己规定的）
         *
         * */
        public static class SQMock<T> {
            //创建头节点，尾节点，初始都指向空（因为初始链表中没有节点）
            Node head = null;
            Node tail = null;

            public void addHead(T value) {
                Node cur = new Node(value);
                //头插方法，先判断链表是否为空，若为空直接将插入节点赋给头节点和尾节点
                if (head == null) {
                    head = cur;
                    tail = cur;
                } else {
                    //若是链表不为空，就将要插入的节点插入链表头部，插入节点作为新头部
                    //流程：cur.next-》head,cur<-head，head=cur
                    cur.next = head;
                    head.pre = cur;
                    head = cur;
                }
            }

            //尾插同理
            public void addBottom(T value) {
                Node cur = new Node(value);
                if (head == null) {
                    head = cur;
                    tail = cur;
                } else {
                    //cur插入尾部，旧尾部移动到新尾部
                    cur.pre = tail;
                    tail.next = cur;
                    tail = cur;
                }
            }

            //删除考虑的多
            public T deleteHead() {

                if (head == null) {
                    return null;
                }
                //记录旧head节点，方便滞空和返回
                Node<T> cur = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    //head=head.next只是将head挪到下一个节点，此时新head节点之前还有一个节点，将它的next-》null，head.pre-》null
                    head = head.next;
                    cur.next = null;
                    head.pre = null;
                }
                return cur.value;
            }

            //删除尾部同理
            public T deleteBottom() {
                if (head == null) {
                    return null;
                }
                Node<T> cur = tail;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    tail = tail.pre;
                    cur.pre = null;
                    tail.next = null;
                }
                return cur.value;
            }

            //判断是否为空
            public boolean isEmpty() {
                return head == null;
            }
        }

        //下面的类是真正咱们自己写的队列，就是调用刚才写的五个方法对链表进行操作，并且封装到队列类里
        public static class MyQueue<T> {
            public SQMock<T> myQueue = new SQMock<T>();

            //队列的入队操作，直接调用尾插
            public void push(T value) {
                myQueue.addBottom(value);
            }

            //出队操作直接调用头出
            public T poll() {
                return myQueue.deleteHead();
            }

            //判断为空直接调用判断是否为空
            public boolean isEmpty() {
                return myQueue.isEmpty();
            }
        }

        //下面是自己实现的栈，明确栈和队列本质相同，唯一不同的就是栈是先进后出，队列是先进先出，所以调用不同的方法即可实现
        public static class MyStack<T> {
            public SQMock<T> myStack = new SQMock<T>();

            //压栈操作，直接调用尾插方法
            public void push(T value) {
                myStack.addBottom(value);
            }

            //出栈操作，直接调用尾出方法
            public T pop() {
                return myStack.deleteBottom();
            }

            public boolean isEmpty() {
                return myStack.isEmpty();
            }
        }

        //下列代码就是对比器，和系统的队列和栈进行比较看咱们是否写的对
        public static boolean isEqual(Integer o1, Integer o2) {
            if (o1 == null && o2 != null) {
                return false;
            }
            if (o1 != null && o2 == null) {
                return false;
            }
            if (o1 == null && o2 == null) {
                return true;
            }
            return o1.equals(o2);
        }

        public static void main(String[] args) {
            int oneTestDataNum = 100;
            int value = 10000;
            int testTimes = 100000;
            for (int i = 0; i < testTimes; i++) {
                MyStack<Integer> myStack = new MyStack<Integer>();
                MyQueue<Integer> myQueue = new MyQueue<Integer>();
                Stack<Integer> stack = new Stack<>();
                Queue<Integer> queue = new LinkedList<>();
                for (int j = 0; j < oneTestDataNum; j++) {
                    int nums = (int) (Math.random() * value);
                    if (stack.isEmpty()) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (Math.random() < 0.5) {
                            myStack.push(nums);
                            stack.push(nums);
                        } else {
                            if (!isEqual(myStack.pop(), stack.pop())) {
                                System.out.println("oops!");
                            }
                        }
                    }
                    int numq = (int) (Math.random() * value);
                    if (stack.isEmpty()) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (Math.random() < 0.5) {
                            myQueue.push(numq);
                            queue.offer(numq);
                        } else {
                            if (!isEqual(myQueue.poll(), queue.poll())) {
                                System.out.println("oops!");
                            }
                        }
                    }
                }
            }
            System.out.println("finish!");
        }
    }

    /**
     * 4.用环形数组实现栈和队列
     */
    // 数组实现队列
    public static class MyQueue {

        private int[] arr;
        private int begin;
        private int end;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.begin = 0;
            this.end = 0;
            this.size = 0;
            this.limit = limit;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

        public void add(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了！！！");
            }
            size++;
            arr[end] = value;
            end = nextIndex(end);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了！！！");
            }
            size--;
            int res = arr[begin];
            begin = nextIndex(begin);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    // 栈的特性是先进后出。pop：把栈顶元素弹出栈  push：把元素压入栈  peek：得到栈顶元素的值

    public static class MyStack {
        private int[] arr;
        private int size;
        private final int limit;


        public MyStack(int limit) {
            this.arr = new int[limit];
            this.size = -1;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit - 1) {
                throw new RuntimeException("栈满了，不能再加了！！！");
            }
            size++;
            arr[size] = value;
        }

        public int pop() {
            if (size < 0) {
                throw new RuntimeException("栈空了，不能再拿了！！！");
            }

            int ans = arr[size];
            size--;
            return ans;
        }

        public boolean isEmpty() {
            return size < 0;
        }
    }

    /**
     * 5.实现有getMin功能的栈
     */
    public static class MyStackImpGetMin {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MyStackImpGetMin() {
            this.stack = new Stack<Integer>();
            this.minStack = new Stack<Integer>();
        }

        public void push(Integer value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            } else if (value < getMin()) {
                minStack.push(value);
            }
            stack.push(value);
        }

        public Integer pop() {
            if (this.stack.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int ans = stack.pop();
            // 注意这块必须等于，否则会出错
            if (ans == getMin()) {
                minStack.pop();
            }
            return ans;
        }

        private Integer getMin() {
            if (this.minStack.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return minStack.peek();
        }
    }

    /**
     * 6.两个栈实现队列
     */
    public static class MyQueueImplTwoStack {
        private Stack<Integer> stack1; // 放入
        private Stack<Integer> stack2; // 取出

        public MyQueueImplTwoStack() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        private void pushToPop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }

        public void add(Integer value) {
            stack1.push(value);
            pushToPop();
        }

        public Integer poll() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stack2.pop();
        }

        public int peek() {
            if (stack1.empty() && stack2.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stack2.peek();
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

    }

    /**
     * 7.两个队列实现栈
     */
    public static class MyStackImplTwoQueue {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStackImplTwoQueue() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(Integer value) {
            queue.add(value);
        }

        public int poll() {
            while (queue.size() > 1) {
                help.add(queue.poll());
            }

            Integer ans = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public int peek() {
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int ans = queue.poll();
            help.add(ans);
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 8.用递归行为得到数组中的最大值，并用master公式来估计时间复杂度
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int processLeft = process(arr, L, mid);
        int processRight = process(arr, mid, R);
        return Math.max(processLeft, processRight);
    }

    /**
     * 9.哈希表和有序表使用的code展示,见算法体系学习班27小结介绍
     */
}
