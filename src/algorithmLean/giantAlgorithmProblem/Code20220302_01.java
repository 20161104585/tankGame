package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 暴力递归到动态规划（六）
 * @Author: haole
 * @Date: 2022/3/2 19:21
 */
public class Code20220302_01 {

    /**
     * 给定一个arr的数组，将他们分成两个累加和最近的部分,返回最接近的情况下，较小集合的累计和
     *
     * @param arr
     * @return
     */
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, 0, sum >> 1);
    }

    private static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else {
            int p1 = process(arr, i + 1, rest);
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }


    public static int rightDp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int N = arr.length;
        sum /= 2;
        int[][] dp = new int[N + 1][sum + 1];

        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 += arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }


        return dp[0][sum];
    }

    /**
     * 练习二
     * 给定一个正数数组arr,请把arr中所有数分成两个集合，
     * 如果arr长度为偶数，两个集合包含的个数一样多，
     * 如果arr长度为奇数，两个集合包含的数只差一个
     * 请尽量让两个数组的累加和相等，
     * 返回，
     * 最接近的情况下，较小集合的累加和
     */
    public static int minSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return processMinSum(arr, 0, arr.length / 2, sum >> 1);
        } else {

            return Math.max(processMinSum(arr, 0, arr.length / 2, sum >> 1),
                    processMinSum(arr, 0, arr.length / 2 + 1, sum >> 1));
        }
    }

    public static int processMinSum(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = processMinSum(arr, i + 1, picks, rest);
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = processMinSum(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }

    public static int minSumDp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int N = arr.length;
        int M = (N + 1) / 2;
        sum /= 2;
        int[][][] dp = new int[N + 1][M][sum + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    dp[i][picks][rest] = -1;
                    int p1 = dp[i + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (picks - 1 > 0 && arr[i] <= rest) {
                        next = dp[i][picks - 1][rest - arr[i]];
                    }
                    if (next != -1) {
                        p2 = arr[i] + next;
                    }
                    dp[i][picks][rest] = Math.max(p1, p2);
                }
            }
        }


        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];
        } else {

            return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
        }
    }

    /**
     * N皇后问题
     */
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return processNum(0, record, n);
    }

    private static int processNum(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += processNum(i + 1, record, n);
            }

        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 重要常数优化
     *
     * @param n
     * @return
     */
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return processNum2(limit, 0, 0, 0);
    }

    /**
     * @param limit       几皇后问题
     * @param colLim      之前皇后的影响列
     * @param leftDiaLim  之前皇后的影响左下
     * @param rightDiaLim 之前皇后的影响右下
     * @return
     */
    private static int processNum2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += processNum2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int maxLen = 20;
//        int maxValue = 50;
//        int testTime = 10000;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int len = (int) (Math.random() * maxLen);
//            int[] arr = randomArray(len, maxValue);
//            int ans1 = right(arr);
//            int ans2 = rightDp(arr);
//            if (ans1 != ans2) {
//                printArray(arr);
//                System.out.println(ans1);
//                System.out.println(ans2);
//                System.out.println("Oops!");
//                break;
//            }
//        }
//        System.out.println("测试结束");
        System.out.println(num1(13));
    }
}
