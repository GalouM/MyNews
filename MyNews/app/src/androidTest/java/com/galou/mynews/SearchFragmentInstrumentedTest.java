package com.galou.mynews;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.controllers.dialogs.PickDateDialog;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ActivityResultMatchers.hasResultCode;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.galou.mynews.UncheckViewAction.setChecked;
import static org.junit.Assert.assertThat;

/**
 * Created by galou on 2019-03-25
 */
@RunWith(AndroidJUnit4.class)
public class SearchFragmentInstrumentedTest {
    private Context context;

    @Rule
    public final ActivityTestRule<SearchActivity> searchActivityTestRule = new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void setup(){
        this.context = InstrumentationRegistry.getTargetContext();
    }



    /*
    @Test
    public void checkBeginDateSet(){
        onView(withId(R.id.search_fragment_start_begin_date)).perform(click());
        onView(withClassName(Matchers.equalTo(PickDateDialog.class.getName()))).perform(PickerActions.setDate(2017, 6, 30));

    }
    */

}
