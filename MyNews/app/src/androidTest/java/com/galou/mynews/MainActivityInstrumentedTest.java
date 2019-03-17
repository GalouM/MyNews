package com.galou.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;

import com.galou.mynews.controllers.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private Context context;

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void checkTabLayoutIsDisplayed(){
        onView(ViewMatchers.withId(R.id.main_activity_tabs)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void checkSwipe(){
        onView(ViewMatchers.withId(R.id.main_activity_viewpager)).perform(swipeLeft()).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfSearchButtonIsDisplayed(){
        onView(ViewMatchers.withId(R.id.menu_main_activity_search)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfMoreButtonIsDisplayed(){
        onView(ViewMatchers.withId(R.id.menu_main_activity_more)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNavigationDrawerIsDisplayed(){
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNavigationDrawerShowFragmentPopular() {
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(ViewMatchers.withId(R.id.main_activity_nav_view)).perform(NavigationViewActions
                .navigateTo(R.id.main_activity_drawer_pop));
        onView(ViewMatchers.withId(R.id.most_pop_frag_layout)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)));
    }
    @Test
    public void checkNavigationDrawerShowFragmentTopStories() {
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(ViewMatchers.withId(R.id.main_activity_nav_view)).perform(NavigationViewActions
                .navigateTo(R.id.main_activity_drawer_top));
        onView(ViewMatchers.withId(R.id.top_stories_frag_layout)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)));
    }
    @Test
    public void checkNavigationDrawerShowFragmentSport(){
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(ViewMatchers.withId(R.id.main_activity_nav_view)).perform(NavigationViewActions
                .navigateTo(R.id.main_activity_drawer_sport));
        onView(ViewMatchers.withId(R.id.sport_frag_layout)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)));

    }
}
