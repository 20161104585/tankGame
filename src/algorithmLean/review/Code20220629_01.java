package algorithmLean.review;

import java.util.*;

/**
 * @Description: 堆排序及其相关面试题
 * @Author: haole
 * @Date: 2022/6/29 9:42
 */
public class Code20220629_01 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 4, 6, 9, 7, 8, 13, 11, 15};
        sortElement(arr, 3);
        for (int i : arr) {
            System.out.print(i + ",");
        }

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "胜多负少");
        hashMap.put(2, "以少胜多");

        System.out.println(hashMap.get("afdf"));
        ;
    }


    /**
     * 堆结构的实现
     * 完全二叉树：每一个节点的位置（i）对于左孩子的位置为2*i+1;右孩子的位置为2*i+2;父节点的位置为(i-1)/2
     * <p>
     * 实现heapInsert功能，数组新加一个数，判断其是否大于父节点，大于则与父节点交换，否则结束
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 实现heapify功能，将数组中最大的数下沉到最后并返回，堆大小减一
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        // 先算出左孩子的下标位置
        int left = index * 2 + 1;
        // 如果有左孩子
        while (left < heapSize) {
            // 找出左右子树最大的那个数的下标值
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 最大的孩子与自身比较
            largest = arr[largest] > arr[index] ? largest : index;
            // 如果最大的数是自身，说明下沉到底部了，结束并返回
            if (largest == index) {
                break;
            }
            // 否则两数交换位置
            swap(arr, largest, index);
            // index下沉
            index = largest;
            // 重新计算当前数的左子树下标
            left = index * 2 + 1;
        }

    }

    /**
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 堆排序的实现
     */
    private static void heapSort(int[] arr) {
        // 边界判断
        if (arr == null || arr.length < 2) {
            return;
        }
        // 先调整为大根堆
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // pop 出最大值(即头节点)，让其放到最后位置，并断连它
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // 循环找出最大值并交换断连
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
     * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。
     */
    private static void sortElement(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k + 1);
        for (int i = 0; i < k + 1; i++) {
            priorityQueue.add(arr[i]);
        }
        int j = 0;
        while (!priorityQueue.isEmpty()) {
            arr[j] = priorityQueue.poll();
            if (j + k + 1 < arr.length) {
                priorityQueue.add(arr[j + k + 1]);
            }
            j++;
        }
    }


    /**
     * 线段最大重合问题
     * 给定很多线段，每个线段都有两个数[start, end]，
     * 表示线段开始位置和结束位置，左右都是闭区间
     * 规定：
     * 1）线段的开始和结束位置一定都是整数值
     * 2）线段重合区域的长度必须>=1
     * 返回线段最多重合区域中，包含了几条线段
     */
    private static class Line {
        private int start;
        private int end;

        public Line(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    private static class MyComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxLine(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Line line = new Line(arr[i][0], arr[i][1]);
            lines[i] = line;
        }
        Arrays.sort(lines, new MyComparator());

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
     * 加强堆的实现
     * 加强堆改写，首先需要定义堆数组、反向索引表、堆大小、比较器，构造方法；
     * 其次需要实现，isEmpty判空功能，size获取堆大小功能，contains校验对象是否存在功能（反相索引表实现），peek查看堆顶元素功能，
     * push堆中加入元素功能，pop弹出堆顶功能，resgin某一元素修改后重新调整堆功能，remove移除指定元素功能，
     * 需实现私有方法，heapify，heapInsert，swap（其中实现反向索引表的更新）
     */
    public static class HeapGreater<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public HeapGreater(Comparator<T> c) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comparator = c;
        }

        /**
         * 判空功能
         */
        public boolean isEmpty() {
            return heapSize == 0;
        }

        /**
         * 获取堆大小功能
         */
        public int size() {
            return heapSize;
        }

        /**
         * 校验对象是否存在
         */
        public boolean contains(T obj) {
            return indexMap.containsKey(obj);
        }

        /**
         * 查看堆顶元素
         *
         * @return
         */
        public T peek() {
            return heap.get(0);
        }

        /**
         * 添加元素
         *
         * @param obj
         */
        public void push(T obj) {
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }

        /**
         * 弹出元素
         *
         * @return
         */
        public T pop() {
            T ans = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        /**
         * 修改元素并重新调整堆结构
         *
         * @param obj
         */
        public void resgin(T obj) {
            heapify(indexMap.get(obj));
            heapInsert(indexMap.get(obj));
        }

        /**
         * 移除指定元素功能
         *
         * @param obj
         */
        public void remove(T obj) {
            T replace = heap.get(heapSize - 1);
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--heapSize);
            if (obj != replace) {
                indexMap.put(replace, index);
                heap.set(index, replace);
                resgin(replace);
            }
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, ((index - 1) / 2));
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int lagest = left + 1 < heapSize && comparator.compare(heap.get(left), heap.get(left + 1)) > 0 ? (left + 1) : left;
                lagest = comparator.compare(heap.get(index), heap.get(lagest)) < 0 ? index : lagest;
                if (lagest == index) {
                    break;
                }
                swap(index, lagest);
                index = lagest;
                left = index * 2 + 1;
            }
        }


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
     * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op；
     * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
     *     arr= [3,3,1,2,1,2,5…
     *     op = [T,T,T,T,F,T,F…
     * 依次表示：
     *      3用户购买了一件商品
     *      3用户购买了一件商品
     *      1用户购买了一件商品
     *      2用户购买了一件商品
     *      1用户退货了一件商品
     *      2用户购买了一件商品
     *      5用户退货了一件商品…
     * 一对arr[i]和op[i]就代表一个事件：用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
     * op[i] == F就代表这个用户退货了一件商品
     * 现在你作为电商平台负责人，你想在每一个事件到来的时候，都给购买次数最多的前K名用户颁奖。
     * 所以每个事件发生后，你都需要一个得奖名单（得奖区）。得奖系统的规则：
     *      1，如果某个用户购买商品数为0，但是又发生了退货事件，则认为该事件无效，得奖名单和上一个事件发生后一致，例子中的5用户
     *      2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
     *      3，每次都是最多K个用户得奖，K也为传入的参数，如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
     *      4，得奖系统分为得奖区和候选区，任何用户只要购买数>0，一定在这两个区域中的一个
     *      5，购买数最大的前K名用户进入得奖区，在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
     *      6，如果购买数不足以进入得奖区的用户，进入候选区
     *      7，如果候选区购买数最多的用户，已经足以进入得奖区，该用户就会替换得奖区中购买数最少的用户（大于才能替换），
     *         如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
     *      8，候选区和得奖区是两套时间，因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
     *        从得奖区出来进入候选区的用户，得奖区时间删除，进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
     *        从候选区出来进入得奖区的用户，候选区时间删除，进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
     *      9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，离开是指彻底离开，哪个区域也不会找到该用户
     *      如果下次该用户又发生购买行为，产生>0的购买数，会再次根据之前规则回到某个区域中，进入区域的时间重记
     * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
     */
}
