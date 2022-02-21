package algorithmLean.giantAlgorithmProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 认识一些递归过程
 * @Author: haole
 * @Date: 2022/2/20 20:55
 */
public class Code20220220_01 {
    public static void main(String[] args) {
        //hanoi1(3);
        //func(3, "左", "右", "中");
//        HashSet<String> ans = subsNoRepeat("aabb");
//        for (String s : ans) {
//            System.out.println(s);
//        }
//        List<String> ans = subs("aabb");
//        for (String s : ans) {
//            System.out.println(s);
//        }
        ArrayList<String> perAns = permutation3("acc");
        //ArrayList<String> perAns = permutation("abc");
        for (String s : perAns) {
            System.out.println(s);
        }
    }

    /**
     * n层汉诺塔问题
     */
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    public static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    /**
     * 请把1~n层圆盘 从左到中
     */
    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToMid(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }


    /**
     * 6合1
     */
    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            func(n - 1, other, to, from);
        }
    }

    /**
     * 打印一个字符串的全部子序列
     */
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    private static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        process1(str, index + 1, ans, path);
        process1(str, index + 1, ans, path + String.valueOf(str[index]));
    }

    /**
     * 打印一个字符串的全部子序列,不要重复序列
     */
    public static HashSet<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> ans = new HashSet<>();
        process2(str, 0, ans, path);
        return ans;
    }

    private static void process2(char[] str, int index, HashSet<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        process2(str, index + 1, ans, path);
        process2(str, index + 1, ans, path + String.valueOf(str[index]));
    }

    /**
     * 打印一个字符串的全排列
     */
    public static ArrayList<String> permutation(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        String path = "";
        ArrayList<Character> characters = new ArrayList<>();
        for (Character ch : str) {
            characters.add(ch);
        }
        perProcess(str, path, characters, ans);
        return ans;
    }

    private static void perProcess(char[] str, String path, ArrayList<Character> characters, ArrayList<String> ans) {
        if (characters.size() == 0) {
            ans.add(path);
            return;
        } else {
            int n = characters.size();
            for (int i = 0; i < n; i++) {
                Character cur = characters.get(i);
                characters.remove(i);
                perProcess(str, path + cur, characters, ans);
                characters.add(i, cur);
            }
        }
    }

    /**
     * 优化版本
     *
     * @param s
     * @return
     */
    public static ArrayList<String> permutation2(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        perProcessYh(str, 0, ans);
        return ans;
    }

    private static void perProcessYh(char[] str, int index, ArrayList<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                perProcessYh(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    /**
     * 打印一个字符串的全排列,去掉重复值
     */
    public static HashSet<String> perNoRepeat(String s) {
        HashSet<String> ans = new HashSet<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        String path = "";
        ArrayList<Character> characters = new ArrayList<>();
        for (Character ch : str) {
            characters.add(ch);
        }
        perProcess2(str, path, characters, ans);
        return ans;
    }

    private static void perProcess2(char[] str, String path, ArrayList<Character> characters, HashSet<String> ans) {
        if (characters.size() == 0) {
            ans.add(path);
            return;
        } else {
            int n = characters.size();
            for (int i = 0; i < n; i++) {
                Character cur = characters.get(i);
                characters.remove(i);
                perProcess2(str, path + cur, characters, ans);
                characters.add(i, cur);
            }
        }
    }

    /**
     * 优化版本去重
     *
     * @param s
     * @return
     */
    public static ArrayList<String> permutation3(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        perProcessYh3(str, 0, ans);
        return ans;
    }

    private static void perProcessYh3(char[] str, int index, ArrayList<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    perProcessYh3(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    /**
     * 练习:
     * 给你一个栈,请你逆序一个栈
     * 不能使用额外数据结构,只能使用递归函数
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack == null) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }

    }
}
