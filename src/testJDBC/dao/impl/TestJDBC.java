package testJDBC.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @Description: java连接数据库技术-JDBC
 * @Author: haole
 * @Date: 2022/4/10 10:02
 */
public class TestJDBC {
    public static void main(String[] args) throws Exception {
//        //1.加载驱动
//        Driver driver = new Driver();
//        //2.注册驱动
//        DriverManager.registerDriver(driver);
        //1通过反射将1和2合并
        /** 注意：高版本，会在依赖包中自动加载驱动 程序默认在此路径辖下：...mysql-connector-java-8.0.27.jar!\META-INF\services\java.sql.Driver */
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获得链接
        /**
         * url：统一资源定位符，定位到我们要连接的数据库
         *  1协议       jdbc:mysql
         *  2IP        39.104.162.252
         *  3端口号     3306
         *  4数据库名字  mydev
         *  5参数
         *  协议://ip:端口号/数据库名称?参数名=参数值&参数名=参数值&....
         *  jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
         * user：用户名
         * password：密码
         */

        String url = "jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String user = "dmsTest";
        String password = "Bb123123@.";
        Connection conn = DriverManager.getConnection(url, user, password);
        //4.获得语句对象 Statement
        Statement statement = conn.createStatement();
        //5.执行SQL语句，返回结果
        /**
         * insert delete update 操作都是调用statement.executeUpdate
         */
        String sql = "insert into t_student (sname,sex,age,classno,email) VALUES ('搞钱','男',22,'1','gq@136.com');";
        int rows = statement.executeUpdate(sql);
        System.out.println("影响行数：" + rows);
        //6.释放资源  注意顺序
        statement.close();
        conn.close();
    }
}
