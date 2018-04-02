package com.springclass.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mickknutson on 3/31/16.
 */
public class StringUtils {

    public static String printLineSeparator(){
        return "\n------------------------------------\n";
    }

    public static String printStarLineSeparator(){
        return "\n************************************\n";
    }

    public static String formatTime(final Long time){
        return formatTime(time, Locale.ENGLISH);
    }

    public static String formatTime(final Long time, Locale locale){
        return NumberFormat.getInstance(locale).format(time);
    }



} // The End...
