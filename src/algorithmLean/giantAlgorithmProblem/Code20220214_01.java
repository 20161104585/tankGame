package algorithmLean.giantAlgorithmProblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 贪心算法
 * @Author: haole
 * @Date: 2022/2/14 14:00
 */
public class Code20220214_01 {
    /**
     * 贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。
     * 也就是说，不从整体最优上加以考虑，他所做出的仅是在某种意义上的局部最优解。
     * 贪心算法不是对所有问题都能得到整体最优解，但对范围相当广泛的许多问题他能产生整体最优解或者是整体最优解的近似解。
     */

    public static class StringComp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    /**
     * 给定一个字符串数组，请返回拼接后字典序最小的字符串
     */
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new StringComp());
        String ans = "";
        for (String s : strs) {
            ans += s;
        }
        return ans;
    }

    /**
     * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
     * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     */
    public static class Program {
        private int start;
        private int end;

        public Program() {

        }
    }

    public static int bestArrange(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                /**
                 * compare方法里，遵循一个统一的规范：
                 * 返回负数的时候，认为第一个参数应该排在前边，
                 * 返回正数的时候，认为第二个参数应该排在前边
                 * 返回0的时候，认为无所谓谁在前边
                 */
                return o1.end - o2.end;
            }
        });

        int res = 0;
        int bestTime = 0;
        for (int i = 0; i < programs.length; i++) {
            if (bestTime <= programs[i].start) {
                res++;
                bestTime = programs[i].end;
            }
        }
        return res;
    }

    /**
     * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如
     * 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜
     * 板。一群人想整分整块金 条，怎么分最省铜板？
     * <p>
     * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为
     * 10+20+30=60. 金条要分成10,20,30三个部分。 如果， 先把长
     * 度60的金条分成10和50，花费60 再把长度50的金条分成20和30，
     * 花费50 一共花费110铜板。
     * <p>
     * 但是如果， 先把长度60的金条分成30和30，花费60 再把长度30
     * 金条分成10和20，花费30 一共花费90铜板。
     * 输入一个数组，返回分割的最小代价。
     * <p>
     * 这是一个典型的哈夫曼问题，哈夫曼的思想就是这个题目的贪心策略。
     */
    public static int lessCost(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }

        int res = 0;
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            res += cur;
            queue.add(cur);
        }
        return res;
    }

    /**
     * 获得最大利润
     * 输入：
     * 参数1，正数数组costs ；
     * 参数2，正数数组profits ；
     * 参数3， 正数k ；
     * 参数4，正数m ；
     * <p>
     * costs[i]表示i号项目的花费，
     * profits[i]表示i号项目在扣除花 费之后还能挣到的钱(利润)，
     * k表示你不能并行、只能串行的最多 做k个项目，
     * m表示你初始的资金 。
     * <p>
     * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下 一个 项目。 输出： 你最后获得的最大钱数。
     */
    public static class Program2 {
        private int costs;
        private int profit;

        public Program2(int c, int p) {
            this.costs = c;
            this.profit = p;
        }
    }

    public static int maxProfit(int[] costs, int[] profits, int k, int m) {
        PriorityQueue<Program2> queueCosts = new PriorityQueue<>(new Comparator<Program2>() {
            @Override
            public int compare(Program2 o1, Program2 o2) {
                return o1.costs - o2.costs;
            }
        });

        PriorityQueue<Program2> queueProfit = new PriorityQueue<>(new Comparator<Program2>() {
            @Override
            public int compare(Program2 o1, Program2 o2) {
                return o2.profit - o1.profit;
            }
        });

        for (int i = 0; i < costs.length; i++) {
            queueCosts.add(new Program2(costs[i], profits[i]));
        }

        for (int i = 0; i < k; i++) {
            while (!queueCosts.isEmpty() && queueCosts.peek().costs <= m) {
                queueProfit.add(queueCosts.poll());
            }
            if (queueProfit.isEmpty()) {
                return m;
            }
            m += queueProfit.poll().profit;
        }
        return m;
    }

    /**
     * 2021-03-18：给定一个字符串str，只由‘X’和‘.’两种字符构成。‘
     * X’表示墙，不能放灯，也不需要点亮，‘.’表示居民点，可以放灯，需要点亮。
     * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮。
     * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯。
     */
    public static int minLight(String str) {
        char[] chars = str.toCharArray();
        int minLight = 0;
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == 'X') {
                i++;
            } else {
                minLight++;
                if (i + 1 == chars.length) {
                    break;
                } else {
                    if (chars[i + 1] == 'X') {
                        i += 2;
                    } else {
                        i += 3;
                    }
                }
            }
        }
        return minLight;
    }
}
