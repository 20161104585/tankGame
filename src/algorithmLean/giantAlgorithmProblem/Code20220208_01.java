package algorithmLean.giantAlgorithmProblem;

/**
 * @Description: 前缀树、不基于比较的排序、排序稳定性
 * @Author: haole
 * @Date: 2022/2/8 18:27
 */
public class Code20220208_01 {
    /**
     * 前缀树（prefix tree | trie）
     * 单个字符串中，字符从前到后的加到一颗多叉树上，
     * 字符放在路上，节点上有专属的数据项（常见的是pass和end值）
     * 所有样本都这样添加，如果没有路就新建，如果有路就复用
     * 沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
     */
    public static void main(String[] args) {

    }

    /**
     * 前缀树节点类型
     */
    public static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;

        //char tmp = 'b' (tmp - 'a')
        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
            //0   a
            //1   b
            //...
            //25  z
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        /**
         * 插入一个字符串
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] str = word.toCharArray();

            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 查询该字符串有几个
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node1 node1 = root;
            char[] str = word.toCharArray();
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node1.nexts[path] == null) {
                    return 0;
                }
                node1 = node1.nexts[path];
            }
            return node1.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            Node1 node1 = root;
            char[] str = pre.toCharArray();
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node1.nexts[path] == null) {
                    return 0;
                }
                node1 = node1.nexts[path];
            }
            return node1.pass;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                Node1 node1 = root;
                char[] str = word.toCharArray();
                int path = 0;
                for (int i = 0; i < str.length; i++) {
                    path = str[i] - 'a';
                    if (--node1.nexts[path].pass == 0) {
                        node1.nexts[path] = null;
                        return;
                    }
                    node1 = node1.nexts[path];
                }
                node1.end--;
            }
        }


    }

    /**
     * 不基与比较的排序
     * 1.计数排序
     * 给定一个员工的年龄数组，按年龄排序
     */
    public static void jishuSort(int[] ages) {
        if (ages == null || ages.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ages.length; i++) {
            max = Math.max(max, ages[i]);
        }
        int[] help = new int[max + 1];
        for (int i = 0; i < ages.length; i++) {
            help[ages[i]]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i]-- > 0) {
                ages[index++] = i;
            }
        }
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        int[] help = new int[r - l + 1];
        for (int d = 0; d <= digit; d++) {//有多少位就进出几次
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[j];
                count[j]++;
            }
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }


}
