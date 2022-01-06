package test;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/12/10 10:46
 */
public class Test02 {
    public static void main(String[] args) {
//        Map<String,Integer> map = new HashMap<>();
//        map.put("001",1);
//        map.put("002",10);
//        if(null == map.get("003")){
//            System.out.println("kongle");
//        }
//        if(map.get("001") > 0){
//            System.out.println("审批通过的有：" + map.get("001"));
//        }
        try {
//            String as = "";
//            as = backMessage();
//            if(as.isEmpty()){
//                System.out.println("adfadfadf");
//            }
//            BigDecimal totalAmt = BigDecimal.ZERO;
//            BigDecimal compotAmt = null;
//            totalAmt = totalAmt.add(compotAmt);
//            System.out.println("总额度：" + totalAmt);
        } catch (Exception e) {
            System.out.println("错了！！！！！！" + e);
        }
    }


    public static void sendMessage(String message) {
        try {
            System.out.println("发送消息：" + message);
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("抓到异常了：" + e.getMessage());
            throw e;
        }
    }

    public static String backMessage() {
        return null;
    }
}
