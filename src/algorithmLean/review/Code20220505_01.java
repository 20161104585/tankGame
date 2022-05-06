package algorithmLean.review;

/**
 * @Description: 第一节：位运算和简单排序
 * 题目：
 * <p>
 * 1.实现打印一个整数的二进制
 * <p>
 * 2.给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
 * <p>
 * 3.实现冒泡排序
 * <p>
 * 4.实现选择排序
 * <p>
 * 5.实现插入排序
 * @Author: haole
 * @Date: 2022/5/5 20:48
 */
public class Code20220505_01 {

    public static void main(String[] args) {
        intgBinaryPritn(5435678);
        System.out.println("阶乘结果：" + factorial(4));
        int[] arr = {6, 8, 1, 9, 5, 7, 4, 9, 6, 3, 5, 8, 7, 4, 2};
        printArray(0, arr);
        insertSort(arr);
        printArray(1, arr);
    }

    /**
     * 数组打印
     *
     * @param arr
     */
    public static void printArray(int type, int[] arr) {
        System.out.println(type == 0 ? "初始数组：" : "排序后数组：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 1.实现打印一个整数的二进制
     *
     * @param num
     */
    private static void intgBinaryPritn(int num) {
        /**
         * 解题思路:
         * 当前数字 与上 （1 左移 i 位置）的数，如果结果为0，说明当前数字二进制位在i位置上为0，否则当前数字在该位置上为1，循环打印了
         */
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    /**
     * 2.给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
     *
     * @param N
     * @return
     */
    private static int factorial(int N) {
        if (N == 0) {
            return 0;
        }
        int result = 1;
        for (int i = 1; i <= N; i++) {
            result = result * i;
        }
        return result;
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 3.实现冒泡排序
     * 过程：
     * 在arr[0～N-1]范围上：
     * arr[0]和arr[1]，谁大谁来到1位置；arr[1]和arr[2]，谁大谁来到2位置…arr[N-2]和arr[N-1]，谁大谁来到N-1位置
     * <p>
     * 在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到N-2位置
     * 在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置
     * …
     * 最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置
     * <p>
     * <p>
     * 估算：
     * 很明显，如果arr长度为N，每一步常数操作的数量，依然如等差数列一般
     * 所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
     * <p>
     * 所以冒泡排序的时间复杂度为O(N^2)。
     */
    private static void bubbleSort(int[] arr) {
        //先处理边界条件,数组为空或数组长度为1，不需要排序，直接return
        if (arr == null || arr.length < 2) {
            return;
        }
        //获取数组长度
        int N = arr.length;
        //最外侧循环控制需要重复多少次冒泡过程
        for (int end = N - 1; end >= 0; end--) {
            //内层循环控制执行每次冒泡过程的次数
            //此出需注意，循环从1开始，因为比较是i-1 和 i 比较
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(second - 1, second, arr);
                }
            }
        }
    }

    /**
     * 4.实现选择排序
     * 工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
     * 存放在序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到全部待排序的数据元素排完。
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i; j < N; j++) {
                minValueIndex = arr[minValueIndex] > arr[j] ? j : minValueIndex;

            }
            swap(i, minValueIndex, arr);
        }
    }

    /**
     * 5.实现插入排序
     * 就是检查第i个数字，如果在它的左边的数字比它大，进行交换，
     * 这个动作一直继续下去，直到这个数字的左边数字比它还要小，就可以停止了。
     */
    private static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            int newValueIndex = i;
            while (newValueIndex - 1 >= 0 && arr[newValueIndex] < arr[newValueIndex - 1]) {
                swap(newValueIndex, newValueIndex - 1, arr);
                newValueIndex--;
            }
        }
    }

}
