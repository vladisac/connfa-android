package com.ls.utils;

import com.ls.hacktmcon.model.PreferencesManager;

import org.jetbrains.annotations.NotNull;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    private SimpleDateFormat mDateFormat;
    private TimeZone mTimezone;
    private static DateUtils mUtils;

    public DateUtils() {
        mDateFormat = new SimpleDateFormat("", Locale.ENGLISH);
        mTimezone = PreferencesManager.getInstance().getServerTimeZoneObject();

        TimeZone tz = TimeZone.getDefault();
        mDateFormat.setTimeZone(mTimezone);
    }

    @NotNull
    public static DateUtils getInstance() {
        if (mUtils == null) {
            mUtils = new DateUtils();
        }
        return mUtils;
    }

    @Nullable
    public Date convertEventDayDate(String day) {
        mDateFormat.applyPattern("d-MM-yyyy");

        try {
            return mDateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isToday(long millis) {
        boolean isToday = false;

        Calendar currCalendar = Calendar.getInstance();
        currCalendar.setTimeZone(mTimezone);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.setTimeZone(mTimezone);

        int currYear = currCalendar.get(Calendar.YEAR);
        int currMonth = currCalendar.get(Calendar.MONTH);
        int currDay = currCalendar.get(Calendar.DAY_OF_MONTH);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (currYear == year && currMonth == month && currDay == day) {
            isToday = true;
        }
        return isToday;
    }

    public String getTime(Context context, long millis) {
        if (DateFormat.is24HourFormat(context)) {
            mDateFormat.applyPattern("kk:mm");
            return mDateFormat.format(new Date(millis));
        } else {
            mDateFormat.applyPattern("hh:mm aa");
            return mDateFormat.format(new Date(millis));
        }
    }

    public String getWeekDay(long millis) {
        mDateFormat.applyPattern("EEE");
        return mDateFormat.format(new Date(millis));
    }

    public String getWeekNameAndDate(long millis) {
        mDateFormat.applyPattern("EEE d");
        return mDateFormat.format(new Date(millis));
    }

    public TimeZone getTimeZone() {
        return mTimezone;
    }
}
