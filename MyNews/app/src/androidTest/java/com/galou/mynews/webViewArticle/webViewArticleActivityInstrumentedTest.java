package com.galou.mynews.webViewArticle;

import android.app.Activity;
import android.content.Context;
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
import static org.junit.Assert.assertThat;
import com.galou.mynews.R;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(AndroidJUnit4.class)
public class webViewArticleActivityInstrumentedTest {
    private Context context;

    @Rule
    public final ActivityTestRule<WebViewArticleActivity> webViewActivityTestRule = new ActivityTestRule<>(WebViewArticleActivity.class);

    @Before
    public void setup(){
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void pressBackButton_finishActivity(){
        onView(withContentDescription("Navigate up")).perform(click());
        assertThat(webViewActivityTestRule.getActivityResult(), hasResultCode(Activity.RESULT_CANCELED));
    }

    @Test
    public void webView_visible(){
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
        
    }
}
