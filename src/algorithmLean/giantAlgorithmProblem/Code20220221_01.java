package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 暴力递归到动态规划
 * @Author: haole
 * @Date: 2022/2/21 21:56
 */
public class Code20220221_01 {
    public static void main(String[] args) {
        //System.out.println(ways3(5, 2, 4, 6));

        System.out.println(win1(new int[]{2, 5, 9, 4, 6, 8, 9, 54, 6, 4}));
        System.out.println(win2(new int[]{2, 5, 9, 4, 6, 8, 9, 54, 6, 4}));
        System.out.println(win3(new int[]{2, 5, 9, 4, 6, 8, 9, 54, 6, 4}));
    }

    /**
     * 尝试斐波那契数列
     */
    public static int fb(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fb(n - 1) + fb(n - 2);
    }


    /**
     * 机器人移动问题
     */
    public static int ways1(int n, int start, int aim, int k) {
        return process1(start, k, aim, n);
    }

    //机器人当前来到的位置cur
    //机器人还有rest布需要走
    //机器人最终到达位置aim
    //有哪些位置1~n
    //返回：机器人从aim出发，走过rest步之后，最终停在aim方法数，是多少
    public static int process1(int cur, int rest, int aim, int n) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        //rest > 0
        if (cur == 1) {
            return process1(2, rest - 1, aim, n);
        }
        if (cur == n) {
            return process1(n - 1, rest - 1, aim, n);
        }
        //中间位置
        return process1(cur - 1, rest - 1, aim, n) + process1(cur + 1, rest - 1, aim, n);
    }


    /**
     * 增加一张表，沿途递归记录值，优化重复递归过程
     */
    public static int ways2(int n, int start, int aim, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, k, aim, n, dp);
    }

    //机器人当前来到的位置cur
    //机器人还有rest布需要走
    //机器人最终到达位置aim
    //有哪些位置1~n
    //返回：机器人从aim出发，走过rest步之后，最终停在aim方法数，是多少
    public static int process2(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        //之前没算过
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, n, dp);
        } else if (cur == n) {
            ans = process2(n - 1, rest - 1, aim, n, dp);
        } else {
            //中间位置
            ans = process2(cur - 1, rest - 1, aim, n, dp) + process2(cur + 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 最终动态规划版本
     */
    public static int ways3(int n, int start, int aim, int k) {
        if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][k + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }
        return dp[start][k];
    }

    /**
     * 练习
     * 纸牌博弈问题
     * 根据规则返回获胜者的分数
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int frist = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(frist, second);
    }

    //arr[l...r], 先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);

        return Math.max(p1, p2);
    }

    private static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R);
        int p2 = f1(arr, L, R - 1);

        return Math.min(p1, p2);
    }


    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int frist = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(frist, second);
    }

    //arr[l...r], 先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        } else if (L == R) {
            gmap[L][R] = arr[L];
        } else {
            gmap[L][R] = Math.max(arr[L] + g2(arr, L + 1, R, fmap, gmap), arr[R] + g2(arr, L, R - 1, fmap, gmap));
        }


        return gmap[L][R];
    }

    private static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        } else if (L == R) {
            fmap[L][R] = 0;
        } else {
            fmap[L][R] = Math.min(f2(arr, L + 1, R, fmap, fmap), f2(arr, L, R - 1, fmap, fmap));
        }

        return fmap[L][R];
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }

        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }
}
