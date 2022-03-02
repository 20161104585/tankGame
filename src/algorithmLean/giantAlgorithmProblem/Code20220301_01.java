package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 暴力递归到动态规划（五）
 * @Author: haole
 * @Date: 2022/3/1 14:28
 */
public class Code20220301_01 {

    /**
     * 打怪兽掉血问题
     * 样本对应模型
     *
     * @param N 怪兽血量
     * @param M 每次打击会损失0~M滴血
     * @param K 打击次数
     * @return
     */
    public static double dill1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long res = process1(N, M, K);
        return (double) res / all;
    }

    public static long process1(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }


        int res = 0;
        for (int i = 0; i <= M; i++) {
            res += process1(N - i, M, K - 1);
        }
        return res;
    }

    public static double dillDp(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                int res = 0;
                for (int f = 0; f <= M; f++) {
                    if (j - f <= 0) {
                        res += dp[i - 1][j - f];
                    } else {
                        res += (long) Math.pow(M + 1, i - 1);
                    }
                }
                dp[i][j] = res;
            }
        }

        double all = Math.pow(M + 1, K);
        long res = dp[K][0];
        return (double) res / all;
    }

    public static double dillDp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                if (j - 1 - M >= 0) {
                    dp[i][j] -= dp[i - 1][j - 1 - M];
                } else {
                    dp[i][j] -= (long) Math.pow(M + 1, i - 1);
                }
            }
        }

        double all = Math.pow(M + 1, K);
        long res = dp[K][0];
        return (double) res / all;
    }

    /**
     * arr是面值数组，其中的数都是正数且没有重复的，在给定一个aim
     * 每个值都认为是一种面值，且认为张数是无限的
     * 返回组成aim的最少货币数
     * 从左往右的尝试模型
     *
     * @return
     */
    public static int minCoins(int[] arr, int aim) {
        return processMin(arr, 0, aim);
    }

    private static int processMin(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }

        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                int next = processMin(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + zhang);
                }
            }
            return ans;
        }
    }

    /**
     * 有枚举类型的动态规划
     *
     * @param arr
     * @param aim
     * @return
     */
    private static int minCoinsDp(int[] arr, int aim) {

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }

        return dp[0][aim];
    }

    /**
     * 无枚举动态规划
     *
     * @param arr
     * @param aim
     * @return
     */
    private static int minCoinsDp2(int[] arr, int aim) {

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index] + 1]);
                }
            }
        }

        return dp[0][aim];
    }

    /**
     * 给定一个正数 n，求 n 的裂开方法数。
     * 规定：后面的数不能比前面的数小。比如 4 的裂开方法有：1+1+1+1、1+1+2、1+3、2+2、4，5 种，所以返回 5。
     */
    public static int ways1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return processWays(1, n);
    }

    private static int processWays(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += processWays(i, rest - i);
        }
        return ways;
    }

    private static int waysDp(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int i = pre; i < rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    private static int waysDp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }
}
