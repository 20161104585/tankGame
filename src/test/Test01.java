package test;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: haole
 * @Date: 2021/7/21 14:53
 */
public class Test01 {
    private static BigDecimal te = BigDecimal.ZERO;

    public static class TestString{
        private String cur;

        public String getCur() {
            return cur;
        }

        public void setCur(String cur) {
            this.cur = cur;
        }

        @Override
        public String toString() {
            return "TestString{" +
                    "cur='" + cur + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
//        Map<String, Object> res = testCd();
//        System.out.println(res.get("resultMsg") == null ? "为空" : res.get("resultMsg"));
//        BigDecimal ress = BigDecimal.ZERO;
//        ress = (BigDecimal) res.get("tys");
//        System.out.println(ress);
//
//        System.out.println("====================================");
//        String cur = null;
//        TestString testPass = testPass(cur);
//        testPass.toString();
//        System.out.println("====================================");
//        BigDecimal num = new BigDecimal(101);
//        if(BigDecimal.valueOf(100).compareTo(num) > 0){
//            System.out.println("金额小于100");
//        }

//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//        Calendar cal = Calendar.getInstance();
//        Date tmpDate = new Date();
//        cal.setTime(tmpDate);
//        int nowWeek =  cal.get(Calendar.DAY_OF_WEEK) - 1;
//        if(nowWeek<0) nowWeek=0;
//        System.out.println(weekDays[nowWeek]);

//        String managerRole = "";
//        if(managerRole == null || managerRole == ""){
//            System.out.println("空");
//            return;
//        }
//        String[] prodCode = managerRole.split("\\|");
//        String[] prodCode = {};
//        for (String s : prodCode){
//            System.out.println(s);
//        }
//
//        StringJoiner stringJoiner = new StringJoiner(" ");
//        stringJoiner.add("1");
//        stringJoiner.add("1");
//        stringJoiner.add("1");
//        stringJoiner.add("1");
//        String s = stringJoiner.toString();
//        System.out.println(s);

//        String date = "2021-10-01";
//        String year = date.substring(0,4);
//        String mouth = date.substring(5,7);
//        String day = date.substring(8);
//        System.out.println(year);
//        System.out.println(mouth);
//        System.out.println(day);

//        List<TestString> list = new ArrayList<>();
//        for(TestString testString : list){
//            testString.getCur();
//            System.out.println("1222211115151");
//        }
//        List<String> orgList = new ArrayList<>();
//        TestString testString = new TestString();
//        orgList.add(testString.getCur());

        try {
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = dateFormat2.parse("2021-11-23 09:25:32");
            Date endDate = new Date();
            System.out.println(endDate);
            Integer time = getDaysBetweenTwoDate(startDate, new Date());
            System.out.println("时间差值为：" + time);
            if(getDaysBetweenTwoDate(startDate, new Date())>=3){
                System.out.println("mingzhogngadf：" + time);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("捕获发放异常了");
        }
        System.out.println("zdafafadsfasd");
    }

    public static Map<String, Object> testCd() {
        Map<String, Object> map = new HashMap<>();
        //map.put("resultMsg", "");
        //map.put("tys", BigDecimal.ONE);
        te = BigDecimal.ONE;
        return map;
    }
    public static TestString testPass(String cur){
        System.out.println("ok");
        System.out.println("cur:" + cur);
        TestString testString = new TestString();
        testString.setCur(cur);
        System.out.println("no problem!");
        return testString;
    }



    public static TestString testException(){
        try {
            int i = 1/1;
        }catch (Exception e){
            System.out.println("异常了！！！！！！");
        }
        System.out.println("异常后输出！！");
        return null;
    }

    /**
     * 获取两个日期之间相差的天数
     *
     * @param curPrcsDt
     *            起始日期
     * @param lastDate
     *            结束日期
     * @return
     */
    public static Integer getDaysBetweenTwoDate(Date curPrcsDt, Date lastDate) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setTime(curPrcsDt);
        long time1 = cal.getTimeInMillis();

        cal.setTime(lastDate);
        long time2 = cal.getTimeInMillis();
        long days = (time2 - time1) / (1000 * 3600 * 24);
        System.out.println("两个日期之间相差的天数:" + days);
        Integer intDays = Integer.parseInt(String.valueOf(days));
        return intDays;
    }
}
