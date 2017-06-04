package com.lemon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * <p>
 * 可获取当前日期，转化日期格式，解析<code>String</code>型的日期为<code>Date</code>类型等
 * 
 * @author plin
 */

public final class DateUtils {

    public final static String YYYY = "yyyy";

    public final static String MM = "MM";

    public final static String DD = "dd";

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYY_MM_DD1 = "yyyy年MM月dd日";

    public final static String YYYY_MM = "yyyy-MM";

    public final static String HH_MM_SS = "HH:mm:ss";

    public final static String HH_MM = "HH:mm";

    public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYYMM = "yyyymm";

    /**
     * 构造函数
     */
    public DateUtils() {
    }

    /**
     * 日期格式化－将<code>Date</code>类型的日期格式化为<code>String</code>型
     *
     * @param date 待格式化的日期
     * @param pattern 时间样式
     * @return 一个被格式化了的<code>String</code>日期
     */
    public static String format(Date date, String pattern) {
        if (date == null)
            return "";
        else
            return getFormatter(pattern).format(date);
    }

    /**
     * 默认为yyyy-MM-dd的格式化
     *
     * @param date
     * @return
     * @Fixed by luyz
     */
    public static String format(Date date) {
        if (date == null)
            return "";
        else
            return getFormatter("yyyy-MM-dd").format(date);
    }

    public static String formatDate(Date date) {
        if (date == null)
            return "";
        else
            return getFormatter("yyyy年MM月dd日").format(date);
    }

    /**
     * 日期解析－将<code>String</code>类型的日期解析为<code>Date</code>型
     *
     * @param date 待格式化的日期
     * @param pattern 日期样式
     * @exception ParseException 如果所给的字符串不能被解析成一个日期
     * @return 一个被格式化了的<code>Date</code>日期
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        try {
            /**
             * Modifed By FanQinglin 2012年4月11日10:34:20 使其支持201208格式的日期解析
             */
            if (pattern.equals(YYYYMM)) {
                strDate = strDate.substring(0, 4) + "-" + strDate.substring(4);
                return getFormatter(YYYY_MM).parse(strDate);
            }
            /** Modifed By FanQinglin END */
            return getFormatter(pattern).parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException("Method parse in Class DateUtils  err: parse strDate fail.", pe.getErrorOffset());
        }
    }

    /**
     * 默认为yyyy-MM-dd格式的解析
     *
     * @param strDate
     * @return
     * @throws ParseException
     * @Fixed by luyz
     */
    public static Date parse(String strDate) throws ParseException {
        try {
            return getFormatter("yyyy-MM-dd").parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException("Method parse in Class DateUtils  err: parse strDate fail.", pe.getErrorOffset());
        }
    }

    public static String formatDate(String date) {
        Date dt = null;
        if (date != null)
            try {
                dt = parse(date);
                return getFormatter("yyyy年MM月dd日").format(dt);
            } catch (Exception e) {
                // TODO: handle exception
                return "  &nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;  月&nbsp;&nbsp;&nbsp;  日";
            }
        else
            return "  &nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;  月&nbsp;&nbsp;&nbsp;  日";

    }

    /**
     * 获取当前日期
     * 
     * @return 一个包含年月日的<code>Date</code>型日期
     */
    public static synchronized Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取当前日期
     * 
     * @return 一个包含年月日的<code>String</code>型日期，但不包含时分秒。yyyy-mm-dd
     */
    public static String getCurrDateStr() {
        return format(getCurrDate(), YYYY_MM_DD);
    }

    /**
     * 获取当前时间
     * 
     * @return 一个包含年月日时分秒的<code>String</code>型日期。hh:mm:ss
     */
    public static String getCurrTimeStr() {
        return format(getCurrDate(), HH_MM_SS);
    }

    /**
     * 获取当前的时间
     * 
     * @return 一个包含时分的<code>String</code>型日期。hh:mm
     */
    public static String getCurrHMStr() {
        return format(getCurrDate(), HH_MM);
    }

    /**
     * 获取当前完整时间,样式: yyyy－MM－dd hh:mm:ss
     * 
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyy-MM-dd hh:mm:ss
     */
    public static String getCurrDateTimeStr() {
        return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间,不要秒（此处依据安阳局变电运行管理需求）,样式: yyyy－MM－dd hh:mm
     * 
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyy-MM-dd hh:mm
     */
    public static String getCurrDateTimeStr0() {
        return format(getCurrDate(), YYYY_MM_DD_HH_MM);
    }

    /**
     * 获取当前年分 样式：yyyy
     * 
     * @return 当前年分
     */
    public static String getYear() {
        return format(getCurrDate(), YYYY);
    }

    /**
     * 获取当前月分 样式：MM
     * 
     * @return 当前月分
     */
    public static String getMonth() {
        return format(getCurrDate(), MM);
    }

    /**
     * 获取季度
     * 
     * @return
     */
    public static String getJidu(Date date) {
        String month = format(date, MM);
        String jiDu = "";
        if (month.equals("01") || month.equals("02") || month.equals("03")) {
            jiDu = "1";
        }
        if (month.equals("04") || month.equals("05") || month.equals("06")) {
            jiDu = "2";
        }
        if (month.equals("07") || month.equals("08") || month.equals("09")) {
            jiDu = "3";
        }
        if (month.equals("10") || month.equals("11") || month.equals("12")) {
            jiDu = "4";
        }
        return jiDu;

    }

    /**
     * 
     * 方法名： getWeekOfDate <br>
     * 作用：
     * 
     * @return String
     */
    public static String getWeekOfDate(Date date) {
        if (null == date) {
            date = new Date();
        }
        String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[0 > week ? 0 : week];
    }

    /**
     * 获取当前日期号 样式：dd
     * 
     * @return 当前日期号
     */
    public static String getDay() {
        return format(getCurrDate(), DD);
    }

    /**
     * 按给定日期样式判断给定字符串是否为合法日期数据
     * 
     * @param strDate 要判断的日期
     * @param pattern 日期样式
     * @return true 如果是，否则返回false
     */
    public static boolean isDate(String strDate, String pattern) {
        try {
            parse(strDate, pattern);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式年份（格式：yyyy）数据
     * 
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY(String strDate) {
        try {
            parse(strDate, YYYY);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    public static boolean isYYYY_MM(String strDate) {
        try {
            parse(strDate, YYYY_MM);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式的年月日（格式：yyyy-MM-dd）数据
     * 
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY_MM_DD(String strDate) {
        try {
            parse(strDate, YYYY_MM_DD);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式年月日时分秒（格式：yyyy-MM-dd HH:mm:ss）数据
     * 
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY_MM_DD_HH_MM_SS(String strDate) {
        try {
            parse(strDate, YYYY_MM_DD_HH_MM_SS);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    public static boolean isYYYY_MM_DD_HH_MM(String strDate) {
        try {
            parse(strDate, YYYY_MM_DD_HH_MM);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式时分秒（格式：HH:mm:ss）数据
     * 
     * @param strDate 要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isHH_MM_SS(String strDate) {
        try {
            parse(strDate, HH_MM_SS);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 获取一个简单的日期格式化对象
     * 
     * @return 一个简单的日期格式化对象
     */
    private static SimpleDateFormat getFormatter(String parttern) {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     * 
     * @param refenceDate 给定日期（格式为：yyyy-MM-dd）
     * @param intevalDays 间隔天数
     * @return 计算后的日期
     */
    public static String getNextDate(String refenceDate, int intevalDays) {
        try {
            return getNextDate(parse(refenceDate, YYYY_MM_DD), intevalDays);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日前的后intevalDay天的日期
     * 
     * @param refenceDate Date 给定日期
     * @param intevalDays int 间隔天数
     * @return String 计算后的日期
     */
    public static String getNextDate(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + intevalDays);
            return format(calendar.getTime(), YYYY_MM_DD);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取指定日期往
     * 
     * @param refenceDate
     * @param intevalDays
     * @return
     */
    public static String getBeforeDate(Date refenceDate, int intevalDays) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - intevalDays);
            return format(calendar.getTime(), YYYY_MM_DD);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 得到两个时间相差的天数
     */
    public static long getIntevalDays(String startDate, String endDate) {
        try {
            return getIntevalDays(parse(startDate, YYYY_MM_DD), parse(endDate, YYYY_MM_DD));
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 得到两个时间相差的天数
     */
    public static long getIntevalDays(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

            return (diff / (1000 * 60 * 60 * 24));
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 得到两个时间相差的年数(zhouyong)
     */
    @SuppressWarnings("deprecation")
    public static int getIntevalYears(Date startDate, Date endDate) {
        try {
            int a = startDate.getYear();
            int b = endDate.getYear();
            return b - a;
        } catch (Exception ee) {
            return 0;
        }
    }

    /**
     * 得到两个时间相差的小时数
     */
    public static double getIntevalHours(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

            return ((double) diff / (1000 * 60 * 60));
        } catch (Exception ee) {
            return 0.0;
        }
    }

    /**
     * 获取给定日期上个月的最后一天的日期
     * 
     * @param current
     * @return
     * @throws Exception
     */
    public static Date getLastMonthDate(Date date) throws Exception {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.DATE, 1);// 设为当前月的1号
            c.add(Calendar.DATE, -1);// 减一天，变为上月最后一天
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取给定日期上年的最后一天的日期
     * 
     * @param current
     * @return
     * @throws Exception
     */
    public static Date getLastYearDate(Date date) throws Exception {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.YEAR, -1);// 减一年，变为去年的第一天
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取当前日期所在月的第一天的日期
     * 
     * @param current
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getFirstDate(Date current) throws Exception {
        try {
            return new Date(current.getYear(), current.getMonth(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取date日期之前连续指定的日期
     * 
     * @param date 当前日期Date类型 interval 指定之前连续的日期天数
     * @return
     */
    public static String[] getRecentDays(Date date, int interval) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        c.setTime(date);
        String[] days = new String[interval];
        days[0] = DateUtils.format(date, DateUtils.YYYY_MM_DD);
        System.out.println(days[0].substring(5));
        for (int i = 1; i < days.length; i++) {
            c.add(Calendar.DAY_OF_YEAR, -1);
            Date d1 = c.getTime();
            days[i] = DateUtils.format(d1);
            System.out.println(days[i].substring(5));
        }
        return days;
    }

    /**
     * 获取date日期之前连续指定的月份个数
     * 
     * @param date 指定的日期 Date类型 interval 指定之前连续的月份个数
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String[] getRecentMonths(Date date, int interval) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] months = new String[interval];
        months[0] = DateUtils.format(date, DateUtils.YYYY_MM_DD);
        for (int i = 1; i < months.length; i++) {
            c.roll(Calendar.MONTH, -1);
            Date d = c.getTime();
            if (d.getMonth() == 0) {
                c.roll(Calendar.YEAR, -1);
            }
            months[i] = DateUtils.format(d);
            System.out.println(months[i]);
        }
        return months;
    }

    /**
     * 获取指定天数后的日期，不包括周六周日
     * 
     * @param date 指定的日期
     * @param day 时间间隔
     * @return 返回规定时限后的日期
     */
    public static Date getIntervalDate(Date date, int interval) {
        Date applyDate = null;
        Calendar replay = Calendar.getInstance();
        if (date == null) {
            return null;
        }
        replay.setTime(date);
        for (int i = 0; i < interval; i++) {
            replay.roll(Calendar.DAY_OF_YEAR, 1);
            while (replay.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || replay.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                replay.roll(Calendar.DAY_OF_YEAR, 1);
            }
        }
        applyDate = replay.getTime();
        return applyDate;
    }

    /**
     * 组合时间
     * 
     * @param year
     * @param mon
     * @return
     */
    public static String combDate(String year, String mon) {
        if (null == year || "".equals(year)) {
            year = "2009";
        }
        if (null == mon || "".equals(mon)) {
            year = "1";
        }
        return year + "-" + mon + "-1";
    }

    /**
     * 获取本月的最大天数
     * 
     * @param year
     * @param month
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, Integer.valueOf(year));
        time.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        int dayOfMonth = time.getActualMaximum(Calendar.DATE);// 本月最大天数
        return dayOfMonth;
    }

    /**
     * 从给定年开始获取年份列表
     */
    public static List<String> getYears(int givenYear) {
        List<String> yearList = new ArrayList<String>();
        int curYear = Integer.parseInt(getYear());
        for (int i = givenYear; i <= curYear; i++) {
            yearList.add(String.valueOf(i));
        }
        return yearList;
    }

    /**
     * 获取去年，今年，明年的年月，格式为2011年10月
     * 
     * @author 朱强
     */
    public static List<String> getThreeYearMonth() {
        int year = Integer.parseInt(getYear());
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(year - 1);
        ls.add(year);
        ls.add(year + 1);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ls.size(); i++) {
            int value = ls.get(i);
            for (int j = 1; j <= 12; j++) {
                list.add(value + "年" + j + "月");
            }
        }

        return list;
    }

    /**
     * 获取去年，今年，明年的年月，格式为2011年1季度
     * 
     * @author 朱强
     */
    public static List<String> getThreeYearSeason() {

        int year = Integer.parseInt(getYear());
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(year - 1);
        ls.add(year);
        ls.add(year + 1);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ls.size(); i++) {
            int value = ls.get(i);
            for (int j = 1; j <= 4; j++) {
                list.add(value + "年" + j + "季度");
            }
        }
        return list;
    }

    /**
     * 获取当前时间所处季度
     * 
     * @return
     */
    public static String getcurrentSeason() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String st = sdf.format(new Date());
        String t[] = st.split("-");

        String str = "";
        int num = Integer.parseInt(t[1]);
        if (num > 0 && num <= 3) {
            str = t[0] + "年" + 1 + "季";

        }
        if (num > 3 && num <= 6) {
            str = t[0] + "年" + 2 + "季";
        }
        if (num > 6 && num <= 9) {
            str = t[0] + "年" + 3 + "季";
        }
        if (num > 9 && num <= 12) {
            str = t[0] + "年" + 4 + "季";
        }
        return str;
    }

    /**
     * 拿到两个间格时间相距多少月份
     * 
     * @param startyear 开始年
     * @param startmonth开始月
     * @param endyear结束年
     * @param endmonth结束月
     * @return返回月份的list对象，每条数据以yyyymm表示
     */
    @SuppressWarnings("unchecked")
    public static List getMonthList(int startyear, int startmonth, int endyear, int endmonth) {
        List result = new ArrayList();
        for (int i = startyear; i <= endyear; i++) {
            for (int j = startmonth; j <= 12; j++) {
                // 判断是否是当年的数据
                if (startyear == endyear && j > endmonth) {
                    break;
                } else {
                    if (i == endyear && j > endmonth) {
                        break;
                    } else {
                        if (j < 10) {
                            result.add(i + "0" + j);
                        } else {
                            result.add(String.valueOf(i) + String.valueOf(j));
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据传入的日期类型返回2012-03======》Mar-12
     * 
     * @throws Exception
     */
    public static String getEnglishMonth(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String s = "";
        switch (month) {
            case 0:
                s = "一月";
                break;
            case 1:
                s = "二月";
                break;
            case 2:
                s = "三月";
                break;
            case 3:
                s = "四月";
                break;
            case 4:
                s = "五月";
            case 5:
                s = "六月";
                break;
            case 6:
                s = "七月";
            case 7:
                s = "八月";
                break;
            case 8:
                s = "九月";
                break;
            case 9:
                s = "十月";
                break;
            case 10:
                s = "十一月";
                break;
            case 11:
                s = "十二月";
                break;
        }
        return s + "-" + (year + "").substring(2, 4);
    }

    /**
     * 根据传入的日期类型返回2012-03======》Mor-11
     * 
     * @throws Exception
     */
    public static String getEnglishMonth1(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);
        String s = "";
        switch (month) {
            case 0:
                s = "一月";
                break;
            case 1:
                s = "二月";
                break;
            case 2:
                s = "三月";
                break;
            case 3:
                s = "四月";
                break;
            case 4:
                s = "五月";
            case 5:
                s = "六月";
                break;
            case 6:
                s = "七月";
            case 7:
                s = "八月";
                break;
            case 8:
                s = "九月";
                break;
            case 9:
                s = "十月";
                break;
            case 10:
                s = "十一月";
                break;
            case 11:
                s = "十二月";
                break;
        }
        return s + "-" + ((year - 1) + "").substring(2, 4);
    }

    /**
     * 日期类根据传入的日期 2012-02 获得 201202
     */
    public static String formartDate(String date) {
        return date.replace("-", "");
    }

    /**
     * 日期类根据传入的日期 2012 获得 2011
     */
    public static String beforDate(String date) throws Exception {
        if (!date.equals("") && date != null) {
            Date dateTemp = DateUtils.parse(date, DateUtils.YYYY);
            Date beforeMonth = DateUtils.getLastYearDate(dateTemp);
            return DateUtils.format(beforeMonth, DateUtils.YYYY);
        } else {
            return date;
        }
    }

    /**
     * 根据传入日期2012-02 获得上个月的2012-01
     * 
     * @throws Exception
     */
    public static String getForwardMonth(String date) throws Exception {
        if (!date.equals("") && date != null) {
            Date dateTemp = DateUtils.parse(date, DateUtils.YYYY_MM);
            Date beforeMonth = DateUtils.getLastMonthDate(dateTemp);
            return DateUtils.format(beforeMonth, DateUtils.YYYY_MM);
        } else {
            return date;
        }
    }

    /**
     * @author FanQinglin 将当前时间加一个月,返回Date对象. Apr 11, 2012
     * @param date
     * @return 下一月的Date对象
     */
    public static Date getNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);// 加一月，变为下一月
        return c.getTime();
    }

    /**
     * @author FanQinglin 将当前时间加一年,返回Date对象. Apr 11, 2012
     * @param date
     * @return 下一年的Date对象
     */
    public static Date getNextYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);// 加一月，变为下一月
        return c.getTime();
    }

    /***
     * @author 蒋志强 将当前时间向前推进一个月 2012-04-18 ---->2012-03-18
     */
    public static Date getLastmonth(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, -1);
        return cl.getTime();
    }

    /**
     * 获取指定日期往
     * 
     * @param refenceDate
     * @param intevalDays
     * @return
     */
    public static String getBeforeDate(Date refenceDate, int intevalDays, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - intevalDays);
            return format(calendar.getTime(), pattern);
        } catch (Exception ee) {
            return "";
        }
    }
}
