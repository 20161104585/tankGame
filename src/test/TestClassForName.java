package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description: 反射测试
 * @Author: haole
 * @Date: 2022/6/27 14:42
 */
public class TestClassForName {
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public static void main(String[] args) throws Exception {
        String str1 = "延";
        String str2 = "展";
        String operName = "延展期三个月";
        System.out.println(operName.indexOf(str1));

        System.out.println(operName.indexOf(str2));


        Date sDate = DateTimeUtils.parse("2022-02-01",DateTimeUtils.ISO_8601_EXTENDED_DATE_FORMAT);
        Date eDate = DateTimeUtils.parse("2022-06-15",DateTimeUtils.ISO_8601_EXTENDED_DATE_FORMAT);

        System.out.println(DateTimeUtils.getDaysBetweenTwoDate(sDate,eDate));

        //正常的调用
        TestClassForName apple = new TestClassForName();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());


        //使用反射调用
        Class clz = Class.forName("test.TestClassForName");
        Method setPriceMethod = clz.getMethod("setPrice", int.class);

        Constructor appleConstructor = clz.getConstructor();

        Object appleObj = appleConstructor.newInstance();

        setPriceMethod.invoke(appleObj, 14);

        Method getPriceMethod = clz.getMethod("getPrice");

        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
    }
}
