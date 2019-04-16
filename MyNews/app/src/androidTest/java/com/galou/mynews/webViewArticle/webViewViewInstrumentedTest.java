package com.galou.mynews.webViewArticle;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.galou.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.galou.mynews.webViewArticle.WebViewArticleActivity.KEY_URL;

/**
 * Created by galou on 2019-04-16
 */
@RunWith(AndroidJUnit4.class)
public class webViewViewInstrumentedTest {

    private Context context;


    @Rule
    public ActivityTestRule<WebViewArticleActivity> webViewActivityTestRule = new ActivityTestRule<>(WebViewArticleActivity.class, false, false);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent();
        String url = "";
        intent.putExtra(KEY_URL, url);

        webViewActivityTestRule.launchActivity(intent);
    }

    @Test
    public void emptyUrl_snackBarShown(){
        onView(withText(R.string.incorrect_url)).check(matches(isDisplayed()));

    }

}
