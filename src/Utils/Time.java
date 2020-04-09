/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    public static String converToDateTimeFormat(ZonedDateTime zdt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime temp = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        String formatDateTime = temp.format(formatter);
        return formatDateTime;
    }

    
    
    
    
    
    public static Date convertToDate(LocalDate localDate){
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        Date temp = calendar.getTime();
        return temp;
    }
    public static LocalDate convertToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZonedDateTime zoneDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zoneDateTime.toLocalDate();
        return localDate;
    }
    public static ZonedDateTime convertToZonedDateTime(String date){
        String[] dateString = date.split(" ")[0].split("-");
        String[] timeString = date.split(" ")[1].split(":");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int day = Integer.parseInt(dateString[2]);
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        int second = 0;
        LocalDate tempLocalDate = LocalDate.of(year, month, day);
        LocalTime tempLocalTime = LocalTime.of(hour, minute, second);
        LocalDateTime localDateTime = LocalDateTime.of(tempLocalDate, tempLocalTime);
        ZonedDateTime utcTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        ZonedDateTime localizedTime = utcTime.withZoneSameInstant(ZoneId.of(getTimeZone().getID()));
        return localizedTime;
    }
    
    
    public static ArrayList<ZonedDateTime> getBussinessHours(LocalDate inputDate){
        ArrayList<ZonedDateTime> returnList = new ArrayList<ZonedDateTime>();
        ZonedDateTime startTime = ZonedDateTime.of(LocalDateTime.of(inputDate, LocalTime.of(8, 0)), ZoneId.of(getTimeZone().getID()));
        ZonedDateTime endTime = ZonedDateTime.of(LocalDateTime.of(inputDate, LocalTime.of(17, 0)), ZoneId.of(getTimeZone().getID()));
        while(startTime.isBefore(endTime)){
            returnList.add(startTime);
            startTime = startTime.plusMinutes(30);
        }
        return returnList;
    }

}
