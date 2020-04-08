/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Timestamp;
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
//return new Timestamp(date.getTime());
        /**
        String year = "" + date.getYear();
        String month = "" + date.getMonth();
        String day = "" + date.getDate();
        String hour = "" + date.getHours();
        String minute = "" + date.getMinutes();
        String second = "" + date.getSeconds();
        
        if(month.length() == 1){
            month = "0" + month;
        }
        if(day.length() == 1){
            day = "0" + day;
        }
        if(hour.length() == 1){
            hour = "0" + hour;
        }
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        if(second.length() == 1){
            second = "0" + second;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;**/
    }
}
