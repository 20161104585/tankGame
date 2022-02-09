package algorithmLean.giantAlgorithmProblem;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description: 链表及其相关面试题
 * @Author: haole
 * @Date: 2022/2/9 21:11
 */
public class Code20220209_01 {

    /**
     * 使用容器判断该链表是否回文链表
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        while (head != null) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不使用容器求解，额外空间复杂度O(1)
     */
    public static boolean isPalindrome3(Node head) {
        //边界判断
        if (head == null || head.next == null) {
            return true;
        }
        //定义快慢指针
        Node h1 = head;
        Node h2 = head;
        //寻找中点位置
        while (h2.next != null && h2.next.next != null) {
            h1 = h1.next; //h1 -> mid
            h2 = h2.next.next; //h2 -> end
        }

        //逆序后半部分链表
        h2 = h1.next;
        h1.next = null;
        Node temp = null;
        while (h2.next != null) {
            temp = h2.next;
            h2.next = h1;
            h1 = h2;
            h2 = temp;
        }

        //让temp = 后部分头节点
        temp = h1;
        h2 = head;
        //初始化结果
        boolean res = true;
        while (h1 != null && h2 != null) {
            if (h1.value != h2.value) {
                res = false;
                break;
            }
            h1 = h1.next;
            h2 = h2.next;
        }

        //将逆序的后半部分恢复
        h1 = temp.next;
        temp.next = null;
        while (h1 != null) {
            h2 = h1.next;
            h1.next = temp;
            temp = h1;
            h1 = h2;
        }
        return res;
    }

    /**
     * 给定一个链表，请把小于v的值放在链表左边，等于放中间，大于放右边
     * <p>
     * 笔试使用数组，partition
     * 面试使用有限变量实现
     */
    public static Node listPartition(Node head, int pivot) {
        //先定义小头、小尾、等头、等尾、大头、大尾
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = null;

            if ((int) head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if ((int) head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        //小于区域的尾连等于区域的头，等于区域的尾连大于区域的头
        //如果存在小于区域
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT; //下一步，谁去连大于区域的头，谁就变成eT
        }

        //eT变为尽量不为空的尾巴节点
        if (eT != null) {
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }

    /**
     * 常见面试题
     * 使用容器
     */
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            hashMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    /**
     * 不使用容器
     */
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }

        //原链表中间增加复制节点
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        //将复制节点的rand指针挂上
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        //获取新节点头，删除原链表
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
