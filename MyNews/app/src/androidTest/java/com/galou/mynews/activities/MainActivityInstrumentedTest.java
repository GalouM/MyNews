package com.galou.mynews.activities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.galou.mynews.R;
import com.galou.mynews.consultArticles.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

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
    public void checkNavigationDrawerCloseOnPressBack() {
        onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(isRoot()).perform(ViewActions.pressBack());
        //onView(ViewMatchers.withId(R.id.main_activity_drawer)).check(matches(isClosed(Gravity.LEFT)));
    }


}
