package com.galou.mynews.webViewArticle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

/**
 * Created by galou on 2019-04-16
 */
@RunWith(MockitoJUnitRunner.class)
public class WebViewPresenterUnitTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private WebViewContract.View webViewView;

    private String url;
    private WebViewPresenter presenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);

        url = "http://google.com";
        presenter = new WebViewPresenter(webViewView, url);
    }

    @Test
    public void correctUrl_setWebView() throws Exception{
        presenter.setUpUrl();

        verify(webViewView).showWebUrl(url);
    }

    @Test public void noUrl_showSnackBar() throws Exception {
        url = "";
        presenter = new WebViewPresenter(webViewView, url);
        presenter.setUpUrl();

        verify(webViewView).showSnackBar();
    }
}
