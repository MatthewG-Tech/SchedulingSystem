/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 *
 * @author matthewguerra
 */
public class Time {
    public static TimeZone getTimeZone(){
        Calendar now = Calendar.getInstance();
        TimeZone timeZone = now.getTimeZone();
        return timeZone;
    }
    public static String converToDateTimeFormat(Date date){
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(date);
        return currentTime;
    }
    public static Date convertToDate(LocalDate localDate){
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        Date temp = calendar.getTime();
        return temp;
    }
    
    public static Date convertToDate(String date){
        String[] dateString = date.split(" ")[0].split("-");
        String[] timeString = date.split(" ")[1].split(":");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]) - 1;
        int day = Integer.parseInt(dateString[2]);
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        int second = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date temp = calendar.getTime();
        temp.setHours(hour);
        temp.setMinutes(minute);
        temp.setSeconds(second);
        return temp;
    }
    
    public static void addFifteenMinutes(Date date){
        if(date.getMinutes() > 45){
            date.setHours(date.getHours() + 1);
            date.setMinutes(date.getMinutes() - 45);
        }else{
            date.setMinutes(date.getMinutes() + 15);
        }
    }
    
    public static ArrayList<Date> getBussinessHours(Date date){
        ArrayList<Date> returnList = new ArrayList<Date>();
        date.setHours(8);
        date.setMinutes(0);
        date.setSeconds(0);
        
        Date endOfDay = (Date) date.clone();
        endOfDay.setHours(17);
        endOfDay.setMinutes(0);
        endOfDay.setSeconds(0);
        date.setYear(endOfDay.getYear());
        while(date.before(endOfDay)){
            Date tempDate = (Date) date.clone();
            tempDate.setHours(date.getHours());
            tempDate.setMinutes(date.getMinutes());
            tempDate.setSeconds(date.getSeconds());
            returnList.add(tempDate);
            if(date.getMinutes() == 30){
                date.setHours(date.getHours() + 1);
                date.setMinutes(0);
            }else{
                date.setMinutes(30);
            }
        }
        return returnList;
    }

}
