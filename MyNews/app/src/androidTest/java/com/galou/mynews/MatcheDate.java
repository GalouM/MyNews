package com.galou.mynews;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.DatePicker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by galou on 2019-03-25
 */
public class MatcheDate {
    public static Matcher<View> matchesDate(final int year, final int month, final int day) {
        return new BoundedMatcher<View, DatePicker>(DatePicker.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("matches date:");
            }

            @Override
            protected boolean matchesSafely(DatePicker item) {
                return (year == item.getYear() && month == item.getMonth() && day == item.getDayOfMonth());
            }
        };
    }
}
