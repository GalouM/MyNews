package com.galou.mynews.consultArticles;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.galou.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by galou on 2019-04-16
 */
@RunWith(AndroidJUnit4.class)
public class ArtcleListViewInstrumentedTest {

    private IdlingResource idlingResource;
    private Context context;


    @Rule
    public final ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void recyclerViewIsVisible(){
        onView(withId(R.id.recycler_view_section_article)).check(matches(isDisplayed()));
    }

    private void waitForNetworkCall(){
        this.idlingResource = mainActivityTestRule.getActivity().getEspressoIdlingResourceForMainFragment();
        IdlingRegistry.getInstance().register(idlingResource);
    }
}
