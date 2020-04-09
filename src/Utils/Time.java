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
    public static String converToDateTimeFormat(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = ldt.format(formatter);
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
    public static LocalDateTime convertToLocalDateTime(String date){
        String[] dateString = date.split(" ")[0].split("-");
        String[] timeString = date.split(" ")[1].split(":");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]) - 1;
        int day = Integer.parseInt(dateString[2]);
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        int second = 0;
        LocalDate tempLocalDate = LocalDate.of(year, month, day);
        LocalTime tempLocalTime = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(tempLocalDate, tempLocalTime);
    }
    
    
    public static ArrayList<LocalDateTime> getBussinessHours(LocalDate inputDate){
        ArrayList<LocalDateTime> returnList = new ArrayList<LocalDateTime>();
        LocalDateTime startTime = LocalDateTime.of(inputDate, LocalTime.of(8, 0));
        LocalDateTime endTime = LocalDateTime.of(inputDate, LocalTime.of(17, 0));
        while(startTime.isBefore(endTime)){
            returnList.add(startTime);
            startTime = startTime.plusMinutes(30);
        }
        return returnList;
    }

}
