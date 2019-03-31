package com.galou.mynews.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;

import static com.galou.mynews.utils.DateUtil.convertCalendarForAPI;
import static com.galou.mynews.utils.DateUtil.convertCalendarForDisplay;
import static com.galou.mynews.utils.DateUtil.convertUserDateToCalendar;
import static com.galou.mynews.utils.DateUtil.isDateAfterToday;
import static com.galou.mynews.utils.DateUtil.isEndDateBeforeBeginDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by galou on 2019-03-30
 */
@RunWith(JUnit4.class)
public class DateUtilUnitTest {
    @Test
    public void dateForDisplayRightFormat(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.YEAR, 2019);
        String calString = convertCalendarForDisplay(calendar);

        assertEquals("02/03/2019", calString);
    }

    @Test
    public void dateForAPIRightFormat(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.YEAR, 2019);
        String calString = convertCalendarForAPI(calendar);

        assertEquals("20190203", calString);
    }

    @Test
    public void badDateFromUserSendNullObject(){
        String date1 = "45/67/2019";
        String date2 = "/2019";
        String date3 = "00";
        String date4 = "";
        String date5 = "13/03/2019";
        String date6 = "05/32/2019";

        assertNull(convertUserDateToCalendar(date1));
        assertNull(convertUserDateToCalendar(date2));
        assertNull(convertUserDateToCalendar(date3));
        assertNull(convertUserDateToCalendar(date4));
        assertNull(convertUserDateToCalendar(date5));
        assertNull(convertUserDateToCalendar(date6));
    }

    @Test
    public void goodDateFromUserSendCalendar(){
        String date = "03/03/2019";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Calendar calendarFromUser = convertUserDateToCalendar(date);
        calendarFromUser.set(Calendar.HOUR_OF_DAY, 0);
        calendarFromUser.set(Calendar.SECOND, 0);
        calendarFromUser.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        assertEquals(calendar.getTime(), convertUserDateToCalendar(date).getTime());
    }

    @Test
    public void dateAfterTodaySendTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        assertTrue(isDateAfterToday(calendar));
    }

    @Test
    public void dateTodaySendFalse() {
        Calendar calendar = Calendar.getInstance();

        assertFalse(isDateAfterToday(calendar));
    }

    @Test
    public void dateBeforeTodaySendFalse() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH) -1;
        calendar.set(Calendar.DAY_OF_MONTH, day);

        assertFalse(isDateAfterToday(calendar));
    }

    @Test
    public void endDateBeforeSendTrue(){
        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        beginDate.add(Calendar.DAY_OF_MONTH, 1);

        assertTrue(isEndDateBeforeBeginDate(beginDate, endDate));
    }

    @Test
    public void endDateAfterSendFalse(){
        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 1);

        assertFalse(isEndDateBeforeBeginDate(beginDate, endDate));
    }

    @Test
    public void endDateSameDaySendFalse(){
        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        assertFalse(isEndDateBeforeBeginDate(beginDate, endDate));
    }

    @Test
    public void noEndDateSendFalse(){
        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = null;

        assertFalse(isEndDateBeforeBeginDate(beginDate, endDate));
    }

    @Test
    public void noBeginDateSendFalse(){
        Calendar beginDate = null;
        Calendar endDate = Calendar.getInstance();;

        assertFalse(isEndDateBeforeBeginDate(beginDate, endDate));
    }
}
