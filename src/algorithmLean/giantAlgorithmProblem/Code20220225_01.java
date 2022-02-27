package algorithmLean.giantAlgorithmProblem;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 暴力递归到动态规划（三）
 * @Author: haole
 * @Date: 2022/2/25 21:22
 */
public class Code20220225_01 {
    public static void main(String[] args) {
        System.out.println(jump(7, 7, 10));
        System.out.println(jumpDp(7, 7, 10));
        System.out.println(minTime(new int[]{1, 2, 6}, 20, 1, 5));
    }

    /**
     * 最长回文子序列
     * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
     *
     * @param s
     * @return
     */
    public static int lps(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    public static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R);
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                int temp = j + i;
                int p1 = dp[j + 1][temp - 1];
                int p2 = dp[j][temp - 1];
                int p3 = dp[j + 1][temp];
                int p4 = str[j] != str[temp] ? 0 : (2 + dp[j + 1][temp - 1]);
                dp[j][temp] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                int temp = j + i;
                dp[j][temp] = Math.max(dp[j][temp - 1], dp[j + 1][temp]);
                if (str[j] == str[temp]) {
                    dp[j][temp] = Math.max(dp[j][temp], 2 + dp[j + 1][temp - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    /**
     * 象棋问题
     * 当前来到的位置（x,y）
     * 换剩下rest步需要跳
     * 最终需要来到的位置（a,b）
     * 10*9
     */
    public static int jump(int a, int b, int k) {
        return proces(0, 0, a, b, k);
    }

    public static int proces(int x, int y, int a, int b, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }

        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }

        int ways = proces(x + 2, y + 1, a, b, rest - 1);
        ways += proces(x + 1, y + 2, a, b, rest - 1);
        ways += proces(x - 1, y + 2, a, b, rest - 1);
        ways += proces(x - 2, y + 1, a, b, rest - 1);
        ways += proces(x - 1, y - 2, a, b, rest - 1);
        ways += proces(x - 2, y - 1, a, b, rest - 1);
        ways += proces(x + 2, y - 1, a, b, rest - 1);
        ways += proces(x + 1, y - 2, a, b, rest - 1);
        return ways;
    }

    public static int jumpDp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);

                    dp[x][y][rest] = ways;
                }
            }
        }


        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    /**
     * 首先，给你几个数据：
     * 数组arr：表示几个咖啡机，这几个咖啡机生产一杯咖啡所需要的时间就是数组中的值，例如arr=[2,3,7]就表示第一台咖啡机生产一杯咖啡需要2单位时间，第二台需要3单位时间，第三台需要7单位时间。
     * int N：表示有N个人需要用咖啡机制作咖啡，每人一杯，同时，假设制作完咖啡后，喝咖啡时间为0，一口闷。
     * int a：表示用洗碗机洗一个咖啡杯需要的时间，串行运行。
     * int b：表示咖啡杯也可以不洗，自然晾干的时间，咖啡杯要么洗，要么晾干。
     * 现在，请你求出这N个人从开始用咖啡杯制作咖啡到杯子洗好或者晾干的最少时间？
     */
    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;

        }
    }

    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int minTime(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> queue = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            queue.add(new Machine(0, arr[i]));
        }

        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = queue.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            queue.add(cur);
        }
        return process(drinks, a, b, 0, 0);

    }

    /**
     * @param drinks   每一个员工喝完咖啡的时间，即需要洗杯子的时间
     * @param a        洗一个杯子的时间
     * @param b        自然挥发的时间
     * @param index    0...index 已经变干净了
     * @param washLine 表示洗的机器啥时候可以用
     * @return
     */
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length) {
            return 0;
        }
        int selfClean1 = Math.max(drinks[index], washLine) + a;
        int restClean1 = process(drinks, a, b, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        int selfClean2 = drinks[index] + b;
        int restClean2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1, p2);
    }

    /**
     * 动态规划
     *
     * @param drinks
     * @param wash
     * @param air
     * @return
     */
    public static int process2(int[] drinks, int wash, int air) {
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        int N = drinks.length;
        int[][] dp = new int[N + 1][maxFree + 1];

        //dp[N][...] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > maxFree) {
                    continue;
                }
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
