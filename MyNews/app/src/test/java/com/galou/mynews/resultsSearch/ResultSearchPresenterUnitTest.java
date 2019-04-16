package com.galou.mynews.resultsSearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;
import com.galou.mynews.models.SectionTopStories;
import com.galou.mynews.utils.TextUtil;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(MockitoJUnitRunner.class)
public class ResultSearchPresenterUnitTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private ResultSearchContract.View resultView;

    @Mock
    private ArticleSearch article;

    private ResultSearchPresenter presenter;

    private String beginDate;
    private String endDate;
    private String terms;
    private String sections;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        beginDate = "20100101";
        endDate = "20190110";
        terms = TextUtil.convertQueryTermForAPI("usa canada");
        List<String> list = new ArrayList<>();
        list.add("Business");
        list.add("Travel");
        sections = TextUtil.convertListInStringForAPI(list);

        presenter = new ResultSearchPresenter(resultView, beginDate, endDate, terms, sections);

        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void fetchAPI_getListArticles() {
        Observable<SectionSearch> observable = ApiStreams.streamFetchSearch(beginDate, endDate, terms, sections, 0);
        TestObserver<SectionSearch> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        SectionSearch sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResponse().getDocs().isEmpty());

    }

    @Test
    public void articleFound_sendToView(){
        presenter.getArticles();

        verify(resultView).showArticles(presenter.getArticlesForTesting());
    }

    @Test
    public void nextArticle_sendToView(){
        presenter.getArticles();
        presenter.getNextArticles();

        verify(resultView).showNextArticles(presenter.getArticlesForTesting());
    }

    @Test
    public void noArticleFound_showMessage(){
        beginDate = "20501010";
        terms = TextUtil.convertQueryTermForAPI("555tttrrreezzaa");
        presenter = new ResultSearchPresenter(resultView, beginDate, endDate, terms, sections);
        presenter.getArticles();

        verify(resultView).showEmptyNewsMessage();

    }

    @Test
    public void getUrlArticle_sentToView(){
        String url = "http://test";
        when(article.getWebUrl()).thenReturn(url);
        presenter.getUrlArticle(article);

        verify(resultView).showDetailsArticle(url);
    }

    @AfterClass
    public static void after(){
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
    }

}
