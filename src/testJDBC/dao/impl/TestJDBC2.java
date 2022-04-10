package testJDBC.dao.impl;

import testJDBC.bean.TStudent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java连接数据库技术-JDBC --优化版本
 * @Author: haole
 * @Date: 2022/4/10 10:02
 */
public class TestJDBC2 {
    /**
     * url：统一资源定位符，定位到我们要连接的数据库
     * 1协议       jdbc:mysql
     * 2IP        39.104.162.252
     * 3端口号     3306
     * 4数据库名字  mydev
     * 5参数
     * 协议://ip:端口号/数据库名称?参数名=参数值&参数名=参数值&....
     * jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
     * user：用户名
     * password：密码
     */
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://39.104.162.252:3306/mydev?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static String user = "dmsTest";
    private static String password = "Bb123123@.";


    public static void main(String[] args) {
        List<TStudent> res = testSelect();
        for (TStudent tStudent : res) {
            System.out.println(tStudent.toString());
        }
    }

    public static List<TStudent> testSelect() {
        Connection conn = null;
        Statement statement = null;
        List<TStudent> resList = new ArrayList<>();
        try {
            //加载并注册驱动
            Class.forName(driver);
            //获得链接
            conn = DriverManager.getConnection(url, user, password);
            //获得语句对象 Statement
            statement = conn.createStatement();
            //执行SQL语句，返回结果
            String sql = "select * from t_student;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TStudent tStudent = new TStudent();
                tStudent.setSeano(resultSet.getInt("seano"));
                tStudent.setSname(resultSet.getString("sname"));
                tStudent.setSex(resultSet.getString("sex"));
                tStudent.setAge(resultSet.getInt("age"));
                tStudent.setScore(resultSet.getBigDecimal("score"));
                tStudent.setScore2(resultSet.getBigDecimal("score2"));
                tStudent.setInputTime(resultSet.getDate("input_time"));
                tStudent.setLastUpdateTime(resultSet.getTime("last_update_time"));
                resList.add(tStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源  注意顺序
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resList;
    }

    public static int testInsert() {
        int rows = 0;
        Connection conn = null;
        Statement statement = null;
        try {
            //加载并注册驱动
            Class.forName(driver);
            //获得链接
            conn = DriverManager.getConnection(url, user, password);
            //获得语句对象 Statement
            statement = conn.createStatement();
            //执行SQL语句，返回结果
            String sql = "insert into t_student (sname,sex,age,classno,email) VALUES ('搞钱','男',22,'1','gq@136.com');";
            rows = statement.executeUpdate(sql);
            System.out.println("影响行数：" + rows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源  注意顺序
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }
}
