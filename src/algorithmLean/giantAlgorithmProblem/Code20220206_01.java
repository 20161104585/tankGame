package algorithmLean.giantAlgorithmProblem;

import java.util.*;

/**
 * @Description: 堆和堆排序
 * @Author: haole
 * @Date: 2022/2/6 10:33
 */
public class Code20220206_01 {

    /**
     * 堆结构，使用层次名字就叫优先级队列
     * 1.堆的定义
     * <p>
     * 堆的形式满足完全二叉树的定义：
     * 若i < ceil(n/2) ，则节点i为分支节点，否则为叶子节点
     * 叶子节点只可能在最大的两层出现，而最大层次上的叶子节点都依次排列在该层最左侧的位置上
     * 如果有度为1的节点，那么只可能有一个，且该节点只有左孩子
     * <p>
     * 根据堆定义的不同，分为大根堆和小根堆：
     * 大根堆每个节点的值都大于其子节点的值
     * 小根堆每个节点的值都小于其子节点的值
     * 除此之外还有一个重要的内容: 单节点也符合堆的特质
     * <p>
     * 注意：堆结构，其中父亲节点为(i-1)/2,左孩子i*2+1,右孩子为i*2+2
     * <p>
     * 因为满足完全二叉树结构，所以时间复杂度为O(logN)
     */
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 1, 10);
        Student student2 = new Student("lisi", 2, 70);
        Student student3 = new Student("wangwu", 3, 35);

        System.out.println("第一组-----------------------");
        Student[] students = new Student[]{student1, student2, student3};
        Arrays.sort(students, new AgeShengOrder());
        for (Student s : students) {
            System.out.println("Name:" + s.name + " id:" + s.id + " age:" + s.age);
        }
        System.out.println("第二组-----------------------");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.sort(new AgeShengOrder());
        for (Student s : studentList) {
            System.out.println("Name:" + s.name + " id:" + s.id + " age:" + s.age);
        }

        System.out.println("第三组-----------------------");
        //有序表
        TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> a.age - b.age);
        treeMap.put(student1, "我是学生张三");
        treeMap.put(student2, "我是学生李四");
        treeMap.put(student3, "我是学生王五");
        for (Student s : treeMap.keySet()) {
            System.out.println("Name:" + s.name + " id:" + s.id + " age:" + s.age);
        }

        System.out.println("认识系统中的堆结构-----------------------");
        //如果基础类型，无需传比较器，默认小根堆，否则必须定义比较器
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new MyComparator());
        //相当于heapInsert
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(4);
        heap.add(166);
        //查看堆顶是谁，不移除
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        System.out.println("堆排序练习1-----------------------");
        int[] arr = new int[]{5, 2, 4, 6, 9, 7, 8, 13, 11, 15};
        sortElement(arr, 2);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 1.认识比较器
     * compare方法里，遵循一个统一的规范：
     * 返回负数的时候，认为第一个参数应该排在前边，
     * 返回正数的时候，认为第二个参数应该排在前边
     * 返回0的时候，认为无所谓谁在前边
     */
    public static class Student {
        private String name;
        private int id;
        private int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.age = age;
            this.id = id;
        }
    }

    /**
     * 年龄升序比较器
     */
    public static class AgeShengOrder implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
//            if (o1.age < o2.age) {
//                return -1;
//            } else if (o1.age > o2.age) {
//                return 1;
//            } else {
//                return 0;
//            }
            return o1.age - o2.age;
        }
    }

    /**
     * 我的比较器
     */
    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    /**
     * 新加来的数，停在index位置，请依次往上移动，
     * 如果移动到0位置或干不掉自己的父节点了，停
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从index位置，往下看，不断下称
     * 如果我的左右孩子不在比我大，或者我没有孩子了，停
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {//存在左孩子，不确定是否有右孩子，
            //获取左右孩子值最大的那个的下标。
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            //判断父节点和孩子的值谁最大
            largest = arr[largest] > arr[index] ? largest : index;
            //如果最大节点就是父节点，停
            if (largest == index) {
                break;
            }
            //否则与最大孩子交换
            swap(arr, largest, index);
            //index往下变
            index = largest;
            //获取左孩子的下标
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        原始方法
//        O(N*logN)
//        for (int i = 0; i < arr.length; i++) { //O(N)
//            heapInsert(arr, i); //O(logN)
//        }
        //优化方法
        //O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, 0, arr.length);
        }


        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 练习1
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
     * 并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序给定一个int数组A，
     * 同时给定A的大小n和题意中的k，请返回排序后的数组。
     * 思路：使用空间复杂度为O（nlogn）中的堆排序，因为快速排序是随机选取一个数然后左右分段，归并排序是分成n
     * 个只有一个元素的序列，他们与序列顺序关系不大，所以选用堆排序解决。
     * 1.建立由k可元素的小顶堆，然后取出顶上元素
     * 2.堆顶用没有建堆的下一元素替代，重新建堆
     * 3.反复调用，完成排序，此算法因为每个元素移动都在k以内，所以时间复杂度为O（nlogk）
     */
    public static void sortElement(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        PriorityQueue<Integer> head = new PriorityQueue<>(k + 1);
        for (int i = 0; i < k + 1; i++) {
            head.add(arr[i]);
        }
        int j = 0;
        while (!head.isEmpty()) {
            arr[j] = head.remove();
            if (j + k + 1 < arr.length) {
                head.add(arr[j + k + 1]);
            }
            j++;
        }
    }

    /**
     * 练习2
     * 题目：最大线段重合问题
     * 给定很多线段，每个线段都有两个数[start,end].表示线段开始位置和结束位置，左右都是闭区间，
     * 规定：1）线段的开始和结束位置一定都是整数值
     * 2）线段重合区域的长度必须>=1
     * 返回线段最多重合区域中，包含了几条线段
     */
    public static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    /**
     * 开始位置比较器
     */
    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    /**
     * 结束位置比较器
     */
    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static int maxCover(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= line.start) {
                priorityQueue.poll();
            }
            priorityQueue.add(line.end);
            max = Math.max(max, priorityQueue.size());
        }

        return max;
    }

    /**
     * 手动改写堆（********非常重要）
     * 加强堆
     */
    public static class HeapGreater<T> {
        //堆数组
        private ArrayList<T> heap;
        //反向索引表
        private HashMap<T, Integer> indexMap;
        //堆大小
        private int heapSize;
        //比较器
        private Comparator<? super T> comp;

        public HeapGreater(Comparator<T> c) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comp = c;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T obj) {
            return indexMap.containsKey(obj);
        }

        public T peek() {
            return heap.get(0);
        }

        public void push(T obj) {
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        public void resgin(T obj) {
            heapInsert(indexMap.get(obj));
            heapify(indexMap.get(obj));
        }

        public void remove(T obj) {
            T replace = heap.get(indexMap.get(heapSize - 1));
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--heapSize);
            if (obj != replace) {
                heap.set(index, replace);
                indexMap.put(replace, index);
                resgin(replace);
            }
        }

        private void heapify(int i) {
            int left = i * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && comp.compare(heap.get(left), heap.get(left + 1)) > 0 ? (left + 1) : left;

                largest = comp.compare(heap.get(i), heap.get(largest)) < 0 ? i : largest;
                if (i == largest) {
                    break;
                }
                swap(i, largest);
                i = largest;
                left = i * 2 + 1;
            }
        }

        public void heapInsert(int i) {
            while (comp.compare(heap.get(i), heap.get((i - 1) / 2)) < 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }


        /**
         * 反向索引表更新放到交换方法里
         *
         * @param i
         * @param j
         */
        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
        }
    }
    /**
     * 练习三
     * 手动改写堆题目练习
     * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op；
     * 两个数组一定等长，假设长度为N，arr[i]表示客户编号,op[i]表示客户操作
     * arr = [3,3,1,2,1,2,5...]
     * op = [T,T,T,T,F,T,F...]
     * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品
     * 1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品
     * 一对arr[i] op[i]就代表一个事件
     * 现在你代表电商平台负责人，你想在每个事件到来的时候，都给购买次数最多的前K名用户颁奖，
     * 所以每个事件发生后，你都获得一个得奖区
     */


}
