package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @Author: haole
 * @Date: 2021/11/15 9:31
 */
public final class DateTimeUtils {


    /**
     * 报文短日期格式
     */
    public static final String MESSAGE_SHORT_PATTERN = "yyyyMMdd";

    /**
     * 报文长日期格式
     */
    public static final String MESSAGE_LONG_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * 报文自定义日期格式
     */
    public static final String MESSAGE_DEFINED_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * ISO 8601 格式
     */
    public static final String ISO_8601_EXTENDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 短日期格式-中文
     */
    public static final String SHORT_PATTERN_CN = "yyyy年MM月dd日";

    /**
     * 短时间格式
     */
    public static final String MESSAGE_PATTEN_SHORT_TIME = "HH:mm:ss";

//    /**
//     * 获取报文交易日期
//     *
//     * @return
//     */
//    public static String getMessageDateStr() {
//        return DateFormatUtils.format(Calendar.getInstance().getTime(), MESSAGE_SHORT_PATTERN);
//    }
//
//    /**
//     * 获取报文交易时间
//     *
//     * @return
//     */
//    public static String getMessageTimestampStr() {
//        return DateFormatUtils.format(Calendar.getInstance().getTime(), MESSAGE_LONG_PATTERN);
//    }

    /**
     * 将指定格式的日期字符串转换为日期对象
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parse(final String date, final String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期对象转换为指定格式字符串
     *
     * @param date
     *            日期对象
     * @param pattern
     *            日期格式
     * @return
     */
    public static String format(final Date date, final String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 获取指定几个月份后的日期<br>
     * 额度终止、还款方式校验等交易使用<br>
     * 例如：<br>
     * <ul>
     * <li>当前为20190130，加1个月后日期为20190228</li>
     * <li>当前为20190228，加1个月后日期为20190331</li>
     * <li>当前为20190630，加1个月后日期为20190731</li>
     * <li>当前为20190629，加1个月后日期为20190729</li>
     * </ul>
     *
     *
     * @param dateStr
     *            日期
     * @param pattern
     *            日期格式
     * @param month
     *            增加几个月份
     * @return
     */
    public static String addMonth(final String dateStr, final String pattern, final int month) {
        Date date = parse(dateStr, pattern);
        Date targetDate = addMonth(date, month);
        return format(targetDate, pattern);
    }

    /**
     * 获取指定几个月份后的日期<br>
     * 额度终止、还款方式校验等交易使用<br>
     * 例如：<br>
     * <ul>
     * <li>当前为20190130，加1个月后日期为20190228</li>
     * <li>当前为20190228，加1个月后日期为20190331</li>
     * <li>当前为20190630，加1个月后日期为20190731</li>
     * <li>当前为20190629，加1个月后日期为20190729</li>
     * </ul>
     *
     * @param date
     *            日期
     * @param month
     *            增加几个月份
     * @return
     */
    public static Date addMonth(final Date date, final int month) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        // 原数据每月最后一天
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 原数据当天日期
        int day = cal.get(Calendar.DATE);

        cal.add(Calendar.MONTH, month);

        // 判断原数据是否为最后一天
        if (day == lastDay) {
            int targetLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DATE, targetLastDay);
        }

        return cal.getTime();
    }

    /**
     * 获取指定几天后的日期<br>
     * <ul>
     * <li>当前为20190130，加1天后日期为20190201</li>
     * </ul>
     *
     * @param date
     *            日期
     * @return
     */
    public static Date addDays(final Date date, final int days) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();

    }

    /**
     * 判断当前日期是否为当前月份最后一天
     *
     * @param dateStr
     *            日期
     * @param pattern
     *            日期格式
     * @return
     */
    public static boolean lastDayOfMonth(final String dateStr, final String pattern) {
        Date date = parse(dateStr, pattern);
        return lastDayOfMonth(date);
    }

    /**
     * 判断当前日期是否为当前月份最后一天
     *
     * @param date
     *            日期
     * @return
     */
    public static boolean lastDayOfMonth(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 每月最后一天
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 当天日期
        int day = cal.get(Calendar.DATE);

        return day == lastDay;
    }

    /**
     * 当前日期是在当前月份的那一天
     *
     * @param date
     *            日期
     * @return
     */
    public static int getDayOfMonth(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 当天日期
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取指定日期加上指定月份后的当月第一天
     *
     *
     * @param date
     *            日期
     * @param month
     *            增加几个月
     * @return
     */
    public static Date getFirstDayOfMonth(final Date date, final int month) {
        Date sourceDate = addMonth(date, month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 原日期加上指定月份与目标日期比较<br>
     * <ul>
     * <li>原日期加上month月 &gt; 目标日期 返回 1</li>
     * <li>原日期加上month月 = 目标日期 返回 0</li>
     * <li>原日期加上month月 &lt; 目标日期 返回 -1</li>
     * </ul>
     *
     * @param sourceStr
     *            日期
     * @param targetStr
     *            日期
     * @param month
     *            指定值
     * @param pattern
     *            日期格式
     * @return
     */
    public static int compareMonthAdd(final String sourceStr, final String targetStr, final int month,
                                      final String pattern) {
        Date source = parse(sourceStr, pattern);
        Date target = parse(targetStr, pattern);
        return compareMonthAdd(source, target, month);
    }

    /**
     * 原日期加上指定月份与目标日期比较<br>
     * <ul>
     * <li>原日期加上month月 &gt; 目标日期 返回 1</li>
     * <li>原日期加上month月 = 目标日期 返回 0</li>
     * <li>原日期加上month月 &lt; 目标日期 返回 -1</li>
     * </ul>
     *
     * @param source
     *            日期
     * @param target
     *            日期
     * @param month
     *            指定值
     * @return
     */
    public static int compareMonthAdd(final Date source, final Date target, final int month) {
        Date sourceDate = addMonth(source, month);
        return sourceDate.compareTo(target);
    }

    /**
     * 两个日期月份差值与指定值进行比较<br>
     * <ul>
     * <li>两个日期月份差值 &gt; 指定值 返回 1</li>
     * <li>两个日期月份差值 = 指定值 返回 0</li>
     * <li>两个日期月份差值 &lt; 指定值 返回 -1</li>
     * </ul>
     *
     * @param sourceStr
     *            日期
     * @param targetStr
     *            日期
     * @param month
     *            指定值
     * @param pattern
     *            日期格式
     * @return
     */
    public static int compareMonthDiff(final String sourceStr, final String targetStr, final int month,
                                       final String pattern) {
        Date source = parse(sourceStr, pattern);
        Date target = parse(targetStr, pattern);
        return compareMonthDiff(source, target, month);
    }

    /**
     * 两个日期月份差值与指定值进行比较<br>
     * <ul>
     * <li>两个日期月份差值 &gt; 指定值 返回 1</li>
     * <li>两个日期月份差值 = 指定值 返回 0</li>
     * <li>两个日期月份差值 &lt; 指定值 返回 -1</li>
     * </ul>
     *
     * @param source
     *            日期
     * @param target
     *            日期
     * @param month
     *            指定值
     * @return
     */
    public static int compareMonthDiff(final Date source, final Date target, final int month) {
        boolean before = source.before(target);

        if (before) {
            Date sourceDate = addMonth(source, month);
            return target.compareTo(sourceDate);
        } else {
            Date targetDate = addMonth(target, month);
            return source.compareTo(targetDate);
        }
    }

    /**
     * 根据输入的起始时间获取耗费时长
     *
     * @param startTime
     * @return
     */
    public static float getTimePeriod(long startTime) {
        if (startTime <= 0) {
            return 0;
        }
        float secondPerids = (float) ((System.currentTimeMillis() - startTime) / 1000.0);
        return secondPerids;

    }

    /**
     * 根据输入的起始时间获取耗费时长
     *
     * @param startTime
     * @return
     */
    public static String getTimePeriodByStartTime(long startTime) {
        float timePeriodByStartTime = getTimePeriod(startTime);
        return String.valueOf(timePeriodByStartTime) + "s";
    }

    /**
     * 获取一个日期的前一天
     *
     * @param Date
     * @return
     */
    public static Date getPreDate(final Date Date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取两个日期之间相差的月数
     *
     * @param datePre
     *            起始日期
     * @param datPost
     *            结束日期
     * @param formatter
     *            格式符
     * @return
     */
    public static int getMonthsBetweenTwoDate(String datePre, String datPost, String formatter) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        Calendar pre = Calendar.getInstance();
        Calendar post = Calendar.getInstance();
        pre.setTime(sdf.parse(datePre));
        post.setTime(sdf.parse(datPost));
        int monthMounts = post.get(Calendar.MONTH) - pre.get(Calendar.MONTH);
        int yearMounts = post.get(Calendar.YEAR) - pre.get(Calendar.YEAR);
        return yearMounts * 12 + monthMounts;
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
        Integer intDays = Integer.parseInt(String.valueOf(days));
        return intDays;
    }
    /**
     * 格式化成  yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatISO8601(Date date) {
        return format(date,ISO_8601_EXTENDED_DATE_FORMAT);
    }

}
