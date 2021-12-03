package algorithmLean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/11/16 15:56
 */
public class Code09_MaxString {

    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     *
     * @param str
     * @return
     */
    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.length() < 1)
            return 0;

        // 记录字符上次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        // 最近出现重复字符的位置
        int pre = -1;

        //adfsefgasdf
        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            Character ch = str.charAt(i);
            Integer index = map.get(ch);
            if (index != null){
                pre = Math.max(pre, index);
            }
            max = Math.max(max, i - pre);
            map.put(ch, i);
        }

        return max;
    }

    public static void main(String[] args) {
        String str = "ngajiksngnjauwegjb";
        int max = lengthOfLongestSubstring(str);
        System.out.println("最长子串为：" + max);
    }
}
