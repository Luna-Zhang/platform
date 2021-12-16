package com.money.more.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date工具类
 *
 * @author lijianan
 */
public class DateUtils {

    /**
     * GTM+8
     */
    public static final String TIME_ZONE = "GTM+8";
    /**
     * yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * Convert Long to String of Day for targeted time zone.
     *
     * @param epochTime
     * @param
     * @return
     */
    public static String epochTimeToDay(long epochTime) {
        Date date = new Date(epochTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * Convert Long to String of Day for targeted time zone.
     *
     * @param epochTime second
     * @param timeZone
     * @return
     */
    public static String epochSecondToDay(long epochTime, String timeZone) {
        Date date = new Date(epochTime * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.format(date);
    }

    /**
     * Convert current Date to String of Day
     *
     * @return
     */
    public static String dateToDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Convert String of day to epoch time for targeted time zone.
     *
     * @param day
     * @param timeZone
     * @return
     * @throws ParseException
     */
    public static long dayToMilliSeconds(String day, String timeZone) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.parse(day).getTime();
    }

    /**
     * Convert String of day to epoch time for targeted time zone.
     *
     * @param day
     * @param
     * @return
     * @throws ParseException
     */
    public static long timetoMilliSeconds(String day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return simpleDateFormat.parse(day).getTime();
    }

    /**
     * Convert String of day to epoch time for targeted time zone.
     *
     * @param day
     * @param timeZone
     * @return
     * @throws ParseException
     */
    public static long dayToSeconds(String day, String timeZone) throws ParseException {
        return dayToMilliSeconds(day, timeZone) / 1000;
    }

    /**
     * Convert String of day to epoch time for UTC
     *
     * @param day
     * @return
     * @throws ParseException
     */
    public static long dayToUTCSeconds(String day) throws ParseException {
        return dayToSeconds(day, "UTC");
    }

    /**
     * Convert String of day to Date type
     *
     * @param time
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String time, String format) throws ParseException {
        DateFormat formater = new SimpleDateFormat(format);
        return formater.parse(time);
    }

    /**
     * Get day of week
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * date to string yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sFormat.format(date);
    }
    public static String DateToString(Date date,String pattern){
        SimpleDateFormat sFormat = new SimpleDateFormat(pattern);
        return sFormat.format(date);
    }

    /**
     * date to string yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String DateToString2(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sFormat.format(date);
    }

    public static String DateToString3(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return sFormat.format(date);
    }

    public static String DateToString4(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
        return sFormat.format(date);
    }

    public static String DateToString5(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat("MM月dd日");
        return sFormat.format(date);
    }

    public static String formDate(Date date, String dateStr) {
        SimpleDateFormat sFormat = new SimpleDateFormat(dateStr);
        return sFormat.format(date);
    }

    /**
     * 比较两个时间
     *
     * @param
     * @param
     */
    public static boolean compareDateTime(Date currentDate, Date endDate) {
        if (currentDate.getTime() > endDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static String decrMonthToString(int count) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -count);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    public static String decrDayToString(int count) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -count);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    public static Date stringToDate(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(time);
        return date;
    }

    public static Date stringToDate2(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = format.parse(time);
        return date;
    }

    /**
     * 获取相差天数
     *
     * @param currentDate
     * @param setDate
     * @return
     */
    public static long getDateTimeDifference(Date currentDate, Date setDate) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        //        long nm = 1000*60;//一分钟的毫秒数
        //        long ns = 1000;//一秒钟的毫秒数long diff;
        //获得两个时间的毫秒时间差异
        long diff = 0;
        try {
            diff = sd.parse(sd.format(currentDate)).getTime()
                    - sd.parse(sd.format(setDate)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        //        long min = diff%nd%nh/nm;//计算差多少分钟
        //        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
        return day;
    }


    /**
     * 获取相差小时
     *
     * @param currentDate
     * @param setDate
     * @return
     */
    public static long getDateTimeHour(Date currentDate, Date setDate) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        //        long nm = 1000*60;//一分钟的毫秒数
        //        long ns = 1000;//一秒钟的毫秒数long diff;
        //获得两个时间的毫秒时间差异
        long diff = 0;

//            long time = sd.parse(sd.format(currentDate)).getTime();
        long time = currentDate.getTime();
//            long time1 = sd.parse(sd.format(setDate)).getTime();
        long time1 = setDate.getTime();
        diff = time - time1;
        //        long day = diff / nd;//计算差多少天
        long hour = diff  / nh;//计算差多少小时
        //        long min = diff%nd%nh/nm;//计算差多少分钟
        //        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
        return hour;
    }




    /**
     * 计算逾期专用
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return daysBetween(sdf.parse(smdate), sdf.parse(bdate));
    }

    /**
     * 计算date2-date1的天数差，忽略时分秒毫秒部分
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetweenIgnoreTime(Date date1, Date date2) {
        return daysBetween(getHeaderDate(date1), getHeaderDate(date2));
    }

    /**
     * date2-date1的天数差,向下取整
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {

        long millis = (date2.getTime() - date1.getTime());
        return (int) (millis / 1000 / 3600 / 24);
    }

    public static int compare_date(Date date1, Date date2) {
        int i = date1.compareTo(date2);
        return i;

    }

    public static int compare_date(String date1Str, Date date2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(date1Str);
        int i = date1.compareTo(date2);
        return i;

    }

    public static Date stringToDate4(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(time);
        return date;
    }

    public static Date stringToDate5(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(time);
        return date;
    }

    /**
     * 如果first > second 返回1
     * first < second 返回 -1
     * first == second 返回 0
     *
     * @param first
     * @param second
     * @return
     */
    public static int equalsDate(Date first, Date second) {
        Calendar calFrist = Calendar.getInstance();
        calFrist.setTime(first);
        Calendar calSecond = Calendar.getInstance();
        calSecond.setTime(second);
        int firstYear = calFrist.get(Calendar.YEAR);
        int secondYear = calSecond.get(Calendar.YEAR);
        int firstDay = calFrist.get(Calendar.DAY_OF_YEAR);
        int secondDay = calSecond.get(Calendar.DAY_OF_YEAR);
        if (firstYear == secondYear) {
            if (firstDay == secondDay) {
                return 0;
            } else {
                if (firstDay > secondDay) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else {
            if (firstYear > secondYear) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static boolean between(Date date, Date begin, Date end) {
        //大于等于begin,小于end
        if (equalsDate(date, begin) == 1 || equalsDate(date, begin) == 0) {
            if (equalsDate(date, end) == -1) {
                return true;
            }
        }
        return false;
    }

    public static Date getTodayHeaderDate() {
        return getHeaderDate(new Date());
    }

    public static Date getHeaderDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTodayFooterDate() {
        return getFooterDate(new Date());
    }

    public static Date getFooterDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 日期加或减 day 天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        return addDate(date, Calendar.DATE, day);
    }

    /**
     * 分钟相加
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        return addDate(date, Calendar.MINUTE, minute);
    }

    /**
     * 当前时间加减操作
     *
     * @param field 时间的部分,参考Calender类
     * @param value 加减的值,正数表示加,负数表示减
     * @return
     */
    public static Date addDate(int field, int value) {
        return addDate(new Date(), field, value);
    }

    /**
     * 指定时间加减操作
     *
     * @param date  指定的时间
     * @param field 时间的部分,参考Calender类
     * @param value 加减的值,正数表示加,负数表示减
     * @return
     */
    public static Date addDate(Date date, int field, int value) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    /**
     * 两个时间相差的秒数，向上取整
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDiffSeconds(Date date1, Date date2) {
        long ms = date2.getTime() - date1.getTime();
        long s = ms / 1000;
        return ms % 1000 == 0 ? s : s + 1;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取当前日期指定天前的时间
     * @return
     */
    public static Date getLastMonthDate(int day){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, -day);
        Date m = c.getTime();
        return m;
    }

    /**
     * 获取当前时间几小时前的时间
     * @param hours
     * @return
     */
    public static Date getTimeByHours(int hours){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, -hours);
        Date m = c.getTime();
        return m;
    }

    /**
     * 获取指定时间多少月前的时间
     * @return
     */
    public static Date getAssignMonthDate(Date date, int month){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -month);
        Date m = c.getTime();
        return m;
    }

    /**
     * 获取当前时间到凌晨的秒数
     * @return
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获得指定日期0点0分0秒
     * @author luochao
     * @date 2019-03-04 16:00
     * @param date
     * @return
     */
    public static Date getTimesmorning(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期23点59分59秒
     * @author luochao
     * @date 2019-03-04 16:00
     * @param date
     * @return
     */
    public static Date getTimesnight(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }


    public static void main(String[] args) throws ParseException {
        /*Date date = DateUtils.strToDate("2018-12-01", "yyyy-MM-dd");

        System.out.println( getDateTimeDifference(new Date(),date));*/

        System.out.println(getSecondsNextEarlyMorning());
        Date date = getAssignMonthDate(new Date(), 1);
        System.out.println(getTimesmorning(date));
        System.out.println(getTimesnight(date));
    }
}
