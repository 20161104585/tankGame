package algorithmLean.giantAlgorithmProblem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: 认识异或运算
 * 异或运算速记法：二进制数相加，相同为0，不同为1；可以记为无进位相加；
 * 异或运算性质：0异或任何数都为任何数，记为：0^N=N
 * 任何数与自己本身异或都等于0，记为N^N=0
 * 异或运算既满足交换律也满足结合律。
 * @Author: haole
 * @Date: 2022/1/6 11:16
 */
public class Code20220106_01 {
    public static void main(String[] args) {
        System.out.println("测试开始");
        long start = System.currentTimeMillis();
        int kinds = 10;
        int range = 200;
        int testTime = 10000;
        int max = 9;
        for (int i = 0; i <= testTime; i++) {
            int a = (int) Math.random() * max + 1; //1~9之间的数
            int b = (int) Math.random() * max + 1; //1~9之间的数
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr2 = randomArray(kinds, range, k, m);
            int ans1 = findMKtest(arr2, k, m);
            int ans2 = findMK(arr2, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                return;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("测试结束: " + (end - start));
    }

    /**
     * Code1:如何不用额外变量交换两个数
     */
    public static void swapNotTemp(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    /**
     * 该交换方法需保证 i != j;否则异或运算会将此位置数刷为0;
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * Code2:一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数。
     */
    public static int findNum(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor = eor ^ i;
        }
        return eor;
    }

    /**
     * Code3:怎么把int类型的数，提取出最右侧的1来。
     * 实现方法：a&(-a) 等价于 a&(~a+1)
     */

    /**
     * Code4:一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数。
     */
    public static void findTwoNum(int[] arr) {
        int eor = 0, eor2 = 0;
        for (int i : arr) {
            eor = eor ^ i;
        }
        int first = eor & (~eor + 1);
        for (int i : arr) {
            if ((first & i) != 0) {
                eor2 = eor2 ^ i;
            }
        }
        System.out.println("a=" + eor2 + ",b=" + (eor ^ eor2));
    }

    /**
     * Code5:一个数组中有一种数出现了K次，其他数都出现了M次，M>1,K<M,怎么找到并打印出现K次的数。要求，额外空间复杂度O(1),时间复杂度O(N)
     */
    public static int findMK(int[] arr, int K, int M) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
//                if (((num >> i) & 1) != 0) {
//                    t[i]++;
//                }
                //上方代码优化后
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((t[i] % M) != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 验证方法
     */
    public static int findMKtest(int[] arr, int K, int M) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == K) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 随机数生成器
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int kTimeNum = randomNumber(range);
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kTimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int j = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;

    }

    /**
     * 获取一个随机数
     */
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
}
