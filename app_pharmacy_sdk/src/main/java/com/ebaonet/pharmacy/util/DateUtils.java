package com.ebaonet.pharmacy.util;


import com.ebaonet.pharmacy.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author Lu
 * @date 2013-2-26
 */
public class DateUtils {

    public static final String HH_MM = "HH:mm";
    public static final String MM_DD = "MM-dd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 活动前N天
     */
    public static final int BEFORE_N = -1;

    /**
     * 促销档期
     * <p/>
     * 类似 " 2013-03-31  至  2013-04-18 ，为期 19 天"
     */
    public static String formatSalesSession(String s, String e, String d) {
        StringBuffer sb = new StringBuffer();
        sb.append(s).append("至").append(e).append("  为期").append(d).append("天");
        return sb.toString();
    }

    /**
     * 格式化日期 类似 “从2013-03-27 至2013-03-31”
     */
    public static String formatStartAndEndTime(String start, String end) {
        StringBuffer sb = new StringBuffer();
        sb.append("从").append(start).append("至").append(end);
        return sb.toString();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        long t = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String st = formatDate(t, pattern);
        return st;
    }

    /**
     * 获取不含分隔符的当前时间字符串
     *
     * @return
     */
    public static String getCurrentTime1() {
        long t = System.currentTimeMillis();
        String pattern = "yyyyMMdd_HHmmss";
        String st = formatDate(t, pattern);
        return st;
    }

    public static String getCurrentTime2() {
        long t = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd HH:mm";
        String st = formatDate(t, pattern);
        return st;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        long t = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd";
        String st = formatDate(t, pattern);
        return st;
    }

    public static String getCurrentDate(SimpleDateFormat pattern) {
        long t = System.currentTimeMillis();
        String st = formatDate(t, pattern);
        return st;
    }

    public static String getCurrentDate1() {
        Date d = new Date();
        int year, month, day;

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        String date = year + "." + month + "." + day;
        return date;

    }

    /**
     * 判断是否是当天
     *
     * @param times 时间戳值
     * @return
     */
    public static boolean isCurrentDay(String times) {
        try {
            long millis = Long.valueOf(times);
            Calendar cal = Calendar.getInstance();
            //Logger.d("=====current.time===" + formatDate(cal.getTimeInMillis()));
            int realDay = cal.get(Calendar.DAY_OF_MONTH);
            // Logger.d("====current.realDay==" + realDay);
            Calendar mTimesCal = Calendar.getInstance();
            mTimesCal.setTimeInMillis(millis);
            int day = mTimesCal.get(Calendar.DAY_OF_MONTH);
            // Logger.d("====current.Day==" + day);
            if (realDay == day) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断是否是当前年
     *
     * @param times
     * @return
     */
    public static boolean isCurrentYear(String times) {
        try {
            long millis = Long.valueOf(times);
            Calendar cal = Calendar.getInstance();
            // Logger.d("=====current.time===" + formatDate(cal.getTimeInMillis()));
            int realYear = cal.get(Calendar.YEAR);
            // Logger.d("====current.realYear==" + realYear);
            Calendar mTimesCal = Calendar.getInstance();
            mTimesCal.setTimeInMillis(millis);
            int year = mTimesCal.get(Calendar.YEAR);
            // Logger.d("====current.Year==" + year);
            if (realYear == year) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDateAndTime() {
        long t = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd HH:mm";
        String st = formatDate(t, pattern);
        return st;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDateAndTime1() {
        long t = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String st = formatDate(t, pattern);
        return st;
    }

    public static String formatTime(String lTime) {
        long t = Long.parseLong(lTime);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String st = formatDate(t, pattern);
        return st;
    }

    /**
     * 格式化时间值
     *
     * @param lDate
     * @return
     */
    public static String formatDate(long lDate, String pattern) {
        Date date = new Date(lDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 格式化时间值
     *
     * @param lDate
     * @return
     */
    public static String formatDate(long lDate, SimpleDateFormat pattern) {
        Date date = new Date(lDate);
        return pattern.format(date);
    }

    /**
     * 格式化时间值
     *
     * @param lDate
     * @return
     */
    public static String formatDate(long lDate) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date(lDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String buildYearAndMonth(int y, int m) {
        StringBuilder sb = new StringBuilder();
        sb.append(y + "");
        sb.append(formatInt(m));
        return sb.toString();
    }

    public static String formatInt(int i) {
        String s = "00";
        if (i >= 0 && i < 10) {
            s = "0" + i;
        } else if (i >= 10) {
            s = i + "";
        }
        return s;
    }

    /**
     * parse string time to long
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long parseDate(String date) {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long ldate = 0;
        try {
            d = dateFormat.parse(date);
            ldate = d.getTime();
        } catch (ParseException e) {
        }
        return ldate;
    }

    /**
     * parse string time to long
     *
     * @param date yyyy-MM-dd HH:mm
     * @return
     */
    public static long parseDateOther(String date) {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long ldate = 0;
        try {
            d = dateFormat.parse(date);
            ldate = d.getTime();
        } catch (ParseException e) {
        }
        return ldate;
    }

    /**
     * parse string time to long
     *
     * @param date
     * @return
     * @throws ParseException 加同步的原因是 多线程操作会影响到日期的解析
     */
    public static synchronized long parseDate(String date, SimpleDateFormat format)
            throws ParseException {
        long ldate = 0;
        Date d = new Date();
        d = format.parse(date);
        ldate = d.getTime();
        return ldate;
    }

    public static Calendar parseCalendar(String date) {
        Calendar cal = null;
        if (StringUtils.isValid(date)) {
            Date d = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                d = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal = Calendar.getInstance();
            cal.setTime(d);
        }
        return cal;
    }

    /**
     * 日期比较: 格式 yyyy-MM-dd date1 < date2 -1 date1 = date2 0 date1 > date2 1
     */
    public static int compareDate(String dateStr1, String dateStr2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = sdf.parse(dateStr1);
            date2 = sdf.parse(dateStr2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date1.getTime() < date2.getTime())
            return -1;
        else if (date1.getTime() == date2.getTime())
            return 0;
        else
            return 1;
    }

    /**
     * 日期比较: 格式 HH:mm date1 < date2 -1 date1 == date2 0 date1 > date2 1
     */
    public static int compareTime(String dateStr1, String dateStr2) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = sdf.parse(dateStr1);
            date2 = sdf.parse(dateStr2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date1.getTime() < date2.getTime())
            return -1;
        else if (date1.getTime() == date2.getTime())
            return 0;
        else
            return 1;
    }

    /**
     * 判断d2是否在d1的range天之内(不含当天)
     *
     * @param d1
     * @param d2
     * @param range
     * @return
     */
    public static boolean isDateInRange(String d1, String d2, int range) {
        Long time1 = null;
        Long time2 = null;
        try {
            time1 = parseDate(d1, yuyue_ymd);
            time2 = parseDate(d2, yuyue_ymd);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        long days = (time2 - time1) / (1000 * 60 * 60 * 24);

        if (days > 0 && days <= range) {
            return true;
        }

        return false;
    }

    /**
     * 格式化预约日期
     */
    public static String formatYuyueDate(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m, d);
        Date date = new Date(cal.getTimeInMillis());
        return yuyue_ymd.format(date);
    }

    /**
     * 截取日期 将"yyyy-MM-dd HH-mm-ss"截断为"yyyy-MM"
     *
     * @return
     */
    public static String truancateDate(String srcDate) {
        String result = "";
        if (null != srcDate && srcDate.length() >= 7) {
            result = srcDate.substring(0, 7);
        }
        return result;
    }

    public static SimpleDateFormat yuyue_ymd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    /**
     * 是否是本周以前
     *
     * @return
     */
    public static boolean isWeekAgo(Date date) {
        Calendar calendar = Calendar.getInstance();
        int curY = calendar.get(Calendar.YEAR);
        int curWY = calendar.get(Calendar.WEEK_OF_YEAR);
        int curWD = calendar.get(Calendar.DAY_OF_WEEK);
        Calendar targetCal = Calendar.getInstance();
        targetCal.setTime(date);
        int targetY = targetCal.get(Calendar.YEAR);
        int targetWY = targetCal.get(Calendar.WEEK_OF_YEAR);
        int targetWD = targetCal.get(Calendar.DAY_OF_WEEK);
        if (curY < targetY) {// 当年小于目标年
            return true;
        } else if (curY == targetY) {// 当年等于目标年
            if (curWY == targetWY) {
                if (curWD == 1 && targetWD != curWD) {
                    return true;
                }
            } else if (curWY > targetWY) {
                return false;
            } else if (curWY < targetWY) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 是否是一周以前
     *
     * @return
     */
    public static boolean isCurDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time = calendar.getTimeInMillis();
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long curTime = calendar.getTimeInMillis();
        return curTime == time;
    }

    /**
     * 信息时间格式化
     *
     * @param time
     * @return
     */
    public static String timeFormat(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat weekAgoFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        SimpleDateFormat curDayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat curWeekFormat = new SimpleDateFormat("E HH:mm");

        try {
            // Date date = dateFormat.parse(time);
            // if (isWeekAgo(date)) {
            // return weekAgoFormat.format(date);
            // } else if (isCurDay(date)) {
            // return curDayFormat.format(date);
            // } else {
            // return curWeekFormat.format(date);
            // }

            Calendar cal1 = Calendar.getInstance();

            String s = isSameDate(dateFormat.format(cal1.getTime()), time);
            Date date = dateFormat.parse(time);
            if ("-1".equals(s)) {
                return weekAgoFormat.format(date);
            } else if ("0".equals(s)) {
                return curDayFormat.format(date);
            } else if ("1".equals(s)) {
                return curWeekFormat.format(date);
            } else {
                return curWeekFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long minuteDifference(String date1, String date2) {
        // 定义时间格式

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 取的两个时间

        Date dt1;
        Date dt2;

        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
            // 取得两个时间的Unix时间

            Long ut1 = dt1.getTime();

            Long ut2 = dt2.getTime();

            // 相减获得两个时间差距的毫秒

            Long timeP = ut2 - ut1;// 毫秒差

            Long sec = timeP / 1000;// 秒差

            Long min = timeP / 1000 * 60;// 分差

            Long hr = timeP / 1000 * 60 * 60;// 时差

            Long day = timeP / 1000 * 60 * 60 * 24;// 日差
            return min;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String msgTimeFormat(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(time);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String isSameDate(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        String s = sameday(cal1, cal2);
        // if("-1".equals(s)){
        // Logger.d("不在同一周") ;
        // }else if("0".equals(s)){
        // Logger.d("同一天") ;
        // }else if("1".equals(s)){
        // Logger.d("同一周") ;
        // }else{
        // Logger.d("我也不知道") ;
        // }
        return s;
    }

    private static String sameday(Calendar cal1, Calendar cal2) {
        boolean flag = false;
        int week1 = cal1.get(Calendar.WEEK_OF_YEAR);
        int week2 = cal2.get(Calendar.WEEK_OF_YEAR);
        if (cal1.getTime().getTime() == cal2.getTime().getTime()) {
            return "0";
        } else {
            if (week1 == week2) {// 按照国际惯例同一周，但按照中国传统不再一周
                int day1 = cal1.get(Calendar.DAY_OF_WEEK);
                int day2 = cal2.get(Calendar.DAY_OF_WEEK);
                if (cal1.getTime().getTime() < cal2.getTime().getTime()) {
                    if (day1 == 1) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                } else if (cal1.getTime().getTime() > cal2.getTime().getTime()) {
                    if (day2 == 1) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            } else {// 按照国际惯例不在同一周，但按照中国传统在一周
                if (week1 - week2 == 1) {
                    int day1 = cal1.get(Calendar.DAY_OF_WEEK);
                    int day2 = cal2.get(Calendar.DAY_OF_WEEK);
                    if (day1 == 1 && day2 != 1) {// 周日
                        flag = true;
                    }

                } else if (week2 - week1 == 1) {
                    int day1 = cal1.get(Calendar.DAY_OF_WEEK);
                    int day2 = cal2.get(Calendar.DAY_OF_WEEK);
                    if (day2 == 1 && day1 != 1) {// 周日
                        flag = true;
                    }
                }
            }
            if (flag) {
                return "1";
            } else {
                return "-1";
            }
        }
    }

    /**
     * @return 获取当前年份
     */
    public static String getCurYear() {
        return "" + Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }


    /**
     * 是否是当前社保年度
     *
     * @param time
     * @return
     */
    public static boolean isCurSiYear(String time) {
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        int curYear = Calendar.getInstance().get(Calendar.YEAR);

        try {
            long curTime = Long.parseLong(time);
            if (curMonth < 6) {
                Calendar cal = Calendar.getInstance();
                cal.set(curYear - 1, 6, 1, 0, 0, 0);
                long minTimes = cal.getTimeInMillis();
                Logger.d("1minTims=" + formatDate(minTimes));
                cal.set(curYear, 5, 30, 0, 0, 0);
                long maxTimes = cal.getTimeInMillis();
                Logger.d("2maxTims=" + formatDate(maxTimes));
                if (curTime >= minTimes && curTime <= maxTimes) {
                    return true;
                }
                return false;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.set(curYear, 6, 1, 0, 0, 0);
                long minTimes = cal.getTimeInMillis();
                Logger.d("3minTims=" + formatDate(minTimes));
                cal.set(curYear + 1, 5, 30, 0, 0, 0);
                long maxTimes = cal.getTimeInMillis();
                Logger.d("4maxTims=" + formatDate(maxTimes));
                if (curTime >= minTimes && curTime <= maxTimes) {
                    return true;
                }
                return false;
            }

        } catch (Exception e) {
        }

        return false;
    }


}
