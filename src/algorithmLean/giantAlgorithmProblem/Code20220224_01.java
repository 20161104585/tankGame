package algorithmLean.giantAlgorithmProblem;

import java.util.HashMap;

/**
 * @Description: 暴力递归到动态规划（二）
 * @Author: haole
 * @Date: 2022/2/24 20:18
 */
public class Code20220224_01 {
    public static void main(String[] args) {
        System.out.println(maxValue1(new int[]{3, 2, 4, 7}, new int[]{5, 6, 3, 19}, 11));
        System.out.println(maxValue2(new int[]{3, 2, 4, 7}, new int[]{5, 6, 3, 19}, 11));
        System.out.println(strResult1("410212"));
        System.out.println(strResult2("410212"));
    }

    /**
     * 练习:背包问题
     * 暴力递归
     *
     * @param w   依次为每个货物的重量
     * @param v   依次为每个货物的价值
     * @param bag 背包的容量，不能超过这个容量
     * @return 不超重的情况下，能得到的最大价值
     */
    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        //尝试函数
        return process1(w, v, 0, bag);
    }

    /**
     * 当前考虑到了index货物， 所有index后的货物都可以自由选择，所做的选择不能超过bag，返回最大价值
     *
     * @param w
     * @param v
     * @param index
     * @param bag
     * @return
     */
    public static int process1(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (w.length == index) {
            return 0;
        }

        //index 没到最后，
        int p1 = process1(w, v, index + 1, bag);
        int p2 = 0;
        int next = process1(w, v, index + 1, bag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int maxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {

                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    /**
     * 规定1和A对应，2和B对应，....，26和Z对应。
     * 那么一个数字111就可以转化为AAA或者为KA或者为AK，
     * 给定一个只有数字字符组成的字符串str，请返回有多少种转换结果
     */
    public static int strResult1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str.toCharArray(), 0);
    }

    /**
     * str[0..i-1]转化无需过问，
     * str[i.....]去转换，返回有多少种方法
     *
     * @param str
     * @param i
     * @return
     */
    private static int process1(char[] str, int i) {
        //之前觉得有效，返回一种结果
        if (str.length == i) {
            return 1;
        }
        //如果i位置面对0字符，之前位置决策失误，返回0结果
        if (str[i] == '0') {
            return 0;
        }
        int ways = process1(str, i + 1);

        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process1(str, i + 2);
        }
        return ways;
    }

    public static int strResult2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }

        return dp[0];
    }

    /**
     * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文。
     * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
     * 返回需要至少多少张贴纸可以完成这个任务。
     * 例子：str= “babac”，arr = {“ba”,“c”,“abcd”}。
     * a + ba + c 3 abcd + abcd 2 abcd+ba 2。所以返回2。
     * <p>
     * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
     */
    public static int minTieNumber1(String str, String[] arr) {
        int ans = process1(str, arr);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String target, String[] stickers) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String s : stickers) {
            String rest = minus(target, s);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(rest, stickers));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char cha : str1) {
            count[cha - 'a']++;
        }
        for (char cha : str2) {
            count[cha - 'a']--;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[j]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }


    public static int minTieNumber2(String str, String[] arr) {
        int N = arr.length;
        //关键优化（用词频数组代表贴纸）
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] s = arr[i].toCharArray();
            for (char cha : s) {
                counts[i][cha - 'a']++;
            }
        }


        int ans = process2(counts, str);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            //最关键优化，（剪枝）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }

    /**
     * 这个问题leetcode上可以直接测
     * 链接：https://leetcode.com/problems/longest-common-subsequence/
     * @param s1
     * @param s2
     * @return
     */
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process1(str1, str2, i - 1, j);

            int p2 = process1(str1, str2, i, j - 1);

            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

}
