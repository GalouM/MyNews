package com.galou.mynews.resultsSearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.galou.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_BEGIN_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_END_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;

/**
 * Created by galou on 2019-04-15
 */
@RunWith(AndroidJUnit4.class)
public class ResultSearchViewInstrumentedTest {

    private Context context;
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<ResultsSearchActivity> resultActivityTestRule = new ActivityTestRule<>(ResultsSearchActivity.class, false, false);

    @Before
    public void setup(){
        this.context = getTargetContext();
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
    public void recyclerViewVisibilityAndScrollable(){
        this.waitForNetworkCall();
        onView(withId(R.id.result_search_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.result_search_recycler_view)).perform(swipeDown());
    }

    @Test
    public void clickRecyclerView_openWebView(){
        this.waitForNetworkCall();
        onView(withId(R.id.result_search_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }

    private void waitForNetworkCall(){
        this.idlingResource = resultActivityTestRule.getActivity().getEspressoIdlingResourceForMainFragment();
        IdlingRegistry.getInstance().register(idlingResource);
    }

}
