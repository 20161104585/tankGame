package testJDBC.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 学生实体类
 * @Author: haole
 * @Date: 2022/4/10 11:19
 */
public class TStudent {

    private int seano;
    private String sname;
    private String sex;
    private int age;
    private BigDecimal score;
    private BigDecimal score2;
    private int classno;
    private String email;
    private Date inputTime;
    private Date lastUpdateTime;

    public int getSeano() {
        return seano;
    }

    public void setSeano(int seano) {
        this.seano = seano;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore2() {
        return score2;
    }

    public void setScore2(BigDecimal score2) {
        this.score2 = score2;
    }

    public int getClassno() {
        return classno;
    }

    public void setClassno(int classno) {
        this.classno = classno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "TStudent{" +
                "seano=" + seano +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", score2=" + score2 +
                ", classno=" + classno +
                ", email='" + email + '\'' +
                ", inputTime=" + inputTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
