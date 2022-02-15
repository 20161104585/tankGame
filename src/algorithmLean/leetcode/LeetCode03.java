package algorithmLean.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 3.无重复字符的最长子串
 * @Author: haole
 * @Date: 2022/2/15 16:20
 */
public class LeetCode03 {
    public static void main(String[] args) {
        String s = "ngajiksngnjauwegjb";
        int max = lengthOfLongestSubstring(s);
        System.out.println("最长子串为：" + max);
    }

    /**
     * 描述：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * leetCode测试地址：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        //先判断临界值
        if (s == null || s.length() < 1) {
            return 0;
        }
        //定义一个map，存放字符最后一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        //初始化最大长度为0
        int max = 0;
        //初始化最近出现重复字符的位置
        int pre = -1;
        //遍历字符
        for (int i = 0; i < s.length(); i++) {
            //获取i位置的字符
            Character str = s.charAt(i);
            //从map中获取该字符上次出现的索引值
            Integer index = map.get(str);
            //如果索引不为空
            if (index != null) {
                //记录最近出现重复字符的位置，谁大是谁。
                pre = Math.max(pre, index);
            }
            //记录不重复字串长度，谁大取谁。
            max = Math.max(max, i - pre);
            //记录本次出现字符的位置到map中
            map.put(str, i);
        }
        //返回结果
        return max;
    }
}
