package com.jeffrey.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 1.8新的日期操作方法
 *
 * @author Jeffrey.Liu
 * @date 2018-09-16 18:17
 **/
public class DateUtils {
    /**
     * 格式化日期时间
     */
    public static void formate(){
        /* 根据给定的值格式化 */
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20181010", formatter1);
        System.out.println(localDate);
        //输出： 2018-10-10

        /* 获取当前日期时间 */
        String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(currentTime);
        //输出： 2018-09-16 18:27:46
    }

    /**
     * 获取当前日期、当前日期时间、当前时间
     */
    public static void get(){
        LocalDate now = LocalDate.now();
        System.out.println(now);
        //输出： 2018-09-16

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        //输出： 2018-09-16T18:30:00.904

        LocalTime now2 = LocalTime.now();
        System.out.println(now2);
        //输出： 18:43:50.129
    }

    /**
     * 获取年、月、日
     */
    public static void getYearMonthDay(){
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        System.out.println("year:"+year+",month:"+month+",day:"+day);
        //输出： year:2018,month:9,day:16
    }

    /**
     * 根据年月日获取指定的日期
     */
    public static void getSomeDay(){
        LocalDate date = LocalDate.of(2018, 9, 16);
        System.out.println(date);
        //输出： 2018-09-16
    }

    /**
     * 判断两个日期是否相等
     */
    public static void equal(){
        LocalDate date1 = LocalDate.of(2018, 9, 16);
        LocalDate date2 = LocalDate.now();
        System.out.println(date1.equals(date2));
        //输出： true
    }

    /**
     * MonthDay的用法，比如每年生日的判断
     */
    public static void monthDay(){
        LocalDate birthDay = LocalDate.of(1989, 1, 1);
        LocalDate now = LocalDate.now();
        MonthDay birth = MonthDay.of(birthDay.getMonth(), birthDay.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(now);
        if (currentMonthDay.equals(birth)){
            System.out.println("今天是你的生日");
        }else{
            System.out.println("今天不是你的生日");
        }

    }

    /**
     * 增加小时数
     */
    public static void addHours(){
        LocalTime now = LocalTime.now();
        LocalTime localTime = now.plusHours(2);
        System.out.println(now);
        System.out.println(localTime);
        //输出：18:45:17.131
        //      20:45:17.131
    }

    /**
     * 获取一周后的日期:plus
     */
    public static void getAfterWeeks(){
        LocalDate now = LocalDate.now();
        LocalDate plus = now.plus(1, ChronoUnit.WEEKS);
        System.out.println(now);
        System.out.println(plus);
        //输出： 2018-09-16
        //       2018-09-23
    }

    /**
     * 获取一年前的日期：minus
     */
    public static void getAfterYears(){
        LocalDate now = LocalDate.now();
        LocalDate minus = now.minus(1, ChronoUnit.YEARS);
        System.out.println(now);
        System.out.println(minus);
        //输出：2018-09-16
        //      2017-09-16
    }

    /**
     * 判断某个日期在另一个日期之前还是之后：isAfter、isBefore
     */
    public static void isBeforeOrAfter(){
        LocalDate now = LocalDate.now();
        LocalDate plus = now.plus(1, ChronoUnit.DAYS);
        System.out.println("日期："+plus+"是否在日期："+now+"之后："+plus.isAfter(now));
        System.out.println("日期："+plus+"是否在日期："+now+"之前："+plus.isBefore(now));
        //输出：日期：2018-09-17是否在日期：2018-09-16之后：true
        //      日期：2018-09-17是否在日期：2018-09-16之前：false
    }

    public static void main(String[] args) {
        isBeforeOrAfter();
    }
}
