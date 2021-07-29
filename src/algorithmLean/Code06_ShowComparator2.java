package algorithmLean;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 比较器、优先级队列、二叉树
 * @Author: haole
 * @Date: 2021/7/28 10:32
 */
public class Code06_ShowComparator2 {

    public static class MyComparator implements Comparator<Integer> {

        //如果返回负数，认为第一个参数应该排在前面
        //如果返回正数，认为第二个参数应该排在前面
        //如果返回0，认为谁排在前面无所谓
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "b";
        //字符串compareTo比较的是字典序
        System.out.println(str1.compareTo(str2));
        System.out.println("=============================");
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(1);
        heap.add(0);
        heap.add(9);
        heap.add(5);
        heap.add(4);
        heap.add(7);
        heap.add(6);
        System.out.println(heap.peek());

        System.out.println("------------------");
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }
}
