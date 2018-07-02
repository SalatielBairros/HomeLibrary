package com.dev.salatiel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static Date toDateTime(String textDate, String format){
        DateFormat iso8601Format = new SimpleDateFormat(format);
        Date retDate = new Date();
        try {
            retDate =  iso8601Format.parse(textDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retDate;
    }

    public static String toString(Date date, String format){
        DateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
