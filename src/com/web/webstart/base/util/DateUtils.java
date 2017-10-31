package com.web.webstart.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author Zzc 14 Nov 2016
 */

public class DateUtils {

	
	/**
	 * 运算月份后的指定日期
	 * @param today
	 * @param diffMonth
	 * @return
	 */
	public static Date diffMonth(Date today, int diffMonth){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + diffMonth);
		return calendar.getTime();
	}
	
	/**
	 * 运算月份后的指定日期，默认为月的第一天
	 * @param diffMonth
	 * @return
	 */
	public static Date diffMonth(int diffMonth){
		return diffMonth(getFirstDayOfMonth(), diffMonth);
	}
	
	/**
	 * 运算小时后的指定日期
	 * @param today
	 * @param diffHour
	 * @return
	 */
	public static Date diffHour(Date today, int diffHour){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + diffHour);

	    showDate(calendar);
		return calendar.getTime();
	}

	/**
	 * 日期转换成时间戳
	 * @param today 日期
	 * @return 时间戳形式的字符串
     */
	public static long date2TimeLong(Date today){
		long time = 0l;
		if(today != null)
			time = today.getTime();
		    
		return time;
	}
	/**
	 * 直接new Date的时候使用此方法
	 * 日期转换成时间戳2
	 * @param today 日期
	 * @return 时间戳形式的字符串
	 */
	public static long date3TimeLong(Date today){
		long time = today.getTime();
		String date = String.valueOf(time);
		date=date.substring(0,10)+"000";
		return Long.parseLong(date);
	}
	
	/**
	 * 运算小时后的指定日期
	 * @param diffHour
	 * @return
	 */
	public static Date diffHour(int diffHour){
		return diffHour(getFirstMinuteOfHour(), diffHour);
	}
	

	/**
	 * 获取本月第一天
	 * @return
	 */
	public static Date getFirstMinuteOfHour(){
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    return cal.getTime() ;
	  }
	
	public static Date getFirstDayOfMonth(){
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.HOUR_OF_DAY, 1);
	    cal.set(Calendar.MINUTE, 1);
	    cal.set(Calendar.SECOND, 1);
	    return cal.getTime() ;
	  }
	public static String getStringTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}
	
	public static void showDate(Calendar cal){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String firstDayOfMonth = sdf.format(cal.getTime());
	    //System.out.println("date:" + firstDayOfMonth);
	}
	
	public static Date strToDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd hh:mm");

        Date date = null;
        try {
            if (strDate.contains(":")) {
                /*Calendar a = Calendar.getInstance();
                strDate = a.get(Calendar.) + "-" + strDate;*/
            	String[] split = getStringTime().split(":");
            	strDate=strDate+":"+split[2];
                date = sdf.parse(strDate);
                
            } else {
            	strDate=strDate+" "+getStringTime();
                date = sdf.parse(strDate);
            }
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

	public static void main(String[] agrs) {
//		DateUtils.diffHour(-1);
//		DateUtils.diffHour(-2);
		Date startDate = DateUtils.diffHour(-216);
		Date middleDate = DateUtils.diffHour(-54);
		Date endDate = DateUtils.diffHour(-1);

		System.out.println(startDate);
		System.out.println(middleDate);
		System.out.println(endDate);
	}
}
