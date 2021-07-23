package test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/7/21 14:53
 */
public class Test01 {
    private static BigDecimal te = BigDecimal.ZERO;

    public static void main(String[] args) {
        Map<String, Object> res = testCd();
        System.out.println(res.get("resultMsg") == null ? "为空" : res.get("resultMsg"));
        BigDecimal ress = BigDecimal.ZERO;
        ress = (BigDecimal) res.get("tys");
        System.out.println(ress);
    }

    public static Map<String, Object> testCd() {
        Map<String, Object> map = new HashMap<>();
        //map.put("resultMsg", "");
        //map.put("tys", BigDecimal.ONE);
        te = BigDecimal.ONE;
        return map;
    }
}
