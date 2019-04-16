package com.galou.mynews.webViewArticle;

import android.content.Intent;
import android.webkit.WebView;

import com.galou.mynews.R;
import com.galou.mynews.resultsSearch.ResultsSearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static com.galou.mynews.webViewArticle.WebViewArticleActivity.KEY_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by galou on 2019-04-16
 */
@RunWith(RobolectricTestRunner.class)
public class WebViewViewUnitTest {

    private WebViewArticleActivity activity;
    private String url;
    private WebView webView;

    @Before
    public void setUp() throws Exception {

        url = "http://google.com";

        Intent intent = new Intent(RuntimeEnvironment.application, ResultsSearchActivity.class);
        intent.putExtra(KEY_URL, url);
        activity = Robolectric.buildActivity(WebViewArticleActivity.class, intent).create().resume().get();
        webView = (WebView) activity.findViewById(R.id.web_view);

    }

    @Test
    public void webView_isVisible() throws Exception {
        assertNotNull(webView);

    }

    @Test
    public void webViewUrl_correctUrl() throws Exception {
        assertEquals(url, webView.getUrl());
    }
}
