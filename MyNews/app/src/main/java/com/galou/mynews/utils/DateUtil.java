package com.galou.mynews.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by galou on 2019-03-30
 */
public abstract class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT_API =
            new SimpleDateFormat("yyyyMMdd", Locale.CANADA);
    private static final SimpleDateFormat DATE_FORMAT_DISPLAY =
            new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);

    private static boolean isDateValidFormat(String date){
        String[] datePart = date.split("/");
        int month = Integer.valueOf(datePart[0]);
        int day = Integer.valueOf(datePart[1]);
        int year = Integer.valueOf(datePart[2]);
        return (month > 0 && month <= 12 && day > 0 && day <= 31 && year >= 1000 && year < 3000);

    }

    public static String convertCalendarForDisplay(Calendar calendar){
        return DATE_FORMAT_DISPLAY.format(calendar.getTime());
    }

    public static String convertCalendarForAPI(Calendar calendar){
        return DATE_FORMAT_API.format(calendar.getTime());
    }

    public static Calendar convertUserDateToCalendar(String dateFromUser){
        Calendar calendar = Calendar.getInstance();
        try {
            if(isDateValidFormat(dateFromUser)) {
                Date date = DATE_FORMAT_DISPLAY.parse(dateFromUser);
                calendar.setTime(date);
                return calendar;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isDateAfterToday(Calendar date){
        Date todayDate = Calendar.getInstance().getTime();
        return todayDate.before(date.getTime());
    }

    public static boolean isEndDateBeforeBeginDate(Calendar beginDate, Calendar endDate){
        if(endDate != null && beginDate != null) {
            return (endDate.getTime().before(beginDate.getTime()));
        } else {
            return false;
        }
    }

}
