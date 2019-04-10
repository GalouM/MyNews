package com.galou.mynews.resultsSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ActivityResultMatchers.hasResultCode;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_BEGIN_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_END_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;
import static org.junit.Assert.assertThat;
import com.galou.mynews.R;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(AndroidJUnit4.class)
public class ResultsSearchActivityInstrumentedTest {
    private Context context;

    @Rule
    public ActivityTestRule<ResultsSearchActivity> resultActivityTestRule = new ActivityTestRule<>(ResultsSearchActivity.class, false, false);

    @Before
    public void setup(){
        this.context = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent();
        String beginDate = "20190101";
        String endDate = "20190102";
        String querySection = "news_desk%3A(\"Arts\")";
        String queryTerm = "test";
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_BEGIN_DATE,beginDate);
        bundle.putString(BUNDLE_KEY_END_DATE,endDate);
        bundle.putString(BUNDLE_KEY_QUERY_SECTIONS,querySection);
        bundle.putString(BUNDLE_KEY_QUERY_TERM,queryTerm);
        intent.putExtras(bundle);
        resultActivityTestRule.launchActivity(intent);
    }

    @Test
    public void pressBackButton_finishActivity(){
        onView(withContentDescription("Navigate up")).perform(click());
        assertThat(resultActivityTestRule.getActivityResult(), hasResultCode(Activity.RESULT_CANCELED));
    }
}
