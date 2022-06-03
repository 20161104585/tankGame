package algorithmLean.review;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 用双链表实现栈和队列
 * @Author: haole
 * @Date: 2022/6/1 17:44
 */
public class Code20220510_02 {

    // 1.首先定义双链表
    public static class Node<T> {
        public Node<T> next;
        public Node<T> pre;
        public T value;

        public Node(T value) {
            this.value = value;
        }
    }

    // 定义一个容器，实现对双链表从头插入、从尾插入、从头删除、从尾删除、判断是否为空等功能
    public static class MyMock<T> {
        Node head = null;
        Node tail = null;

        // 从头插入
        public void addByHead(T value) {
            Node<T> node = new Node<>(value);
            if (this.isEmpty()) {
                head = node;
                tail = node;
            } else {
                head.pre = node;
                node.next = head;
                head = node;
            }
        }

        // 从尾插入
        public void addByTail(T value) {
            Node<T> node = new Node<>(value);
            if (this.isEmpty()) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        // 从头删除
        public T removeByHead() {
            if (this.isEmpty()) {
                return null;
            }

            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
                cur.next = null;
            }
            return cur.value;
        }

        // 从尾删除
        public T removeByTail() {
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

        // 判断是否为空
        public boolean isEmpty() {
            return head == null;
        }

    }

    // 最后实现自己写的队列，队列的入队操作，直接调用尾插，出队操作直接调用头出，判断为空直接调用判断是否为空
    public static class MyQueue<T> {
        public MyMock<T> myMock = new MyMock<>();

        public void push(T value) {
            myMock.addByTail(value);
        }

        public T poll() {
            return myMock.removeByHead();
        }

        public boolean isEmpty() {
            return myMock.isEmpty();
        }
    }

    // 最后实现自己写的栈，压栈操作，直接调用尾插方法，出栈操作，直接调用尾出方法，判断为空直接调用判断是否为空
    public static class MyStack<T> {
        MyMock<T> myMock = new MyMock<>();

        public void push(T value) {
            myMock.addByHead(value);
        }

        public T pop() {
            return myMock.removeByHead();
        }

        public boolean isEmpty() {
            return myMock.isEmpty();
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
            Code20220510_01.Suan03Queue01.MyStack<Integer> myStack = new Code20220510_01.Suan03Queue01.MyStack<Integer>();
            Code20220510_01.Suan03Queue01.MyQueue<Integer> myQueue = new Code20220510_01.Suan03Queue01.MyQueue<Integer>();
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
