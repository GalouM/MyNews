package com.galou.mynews.consultArticles;

import android.view.View;

import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleMostPopular;
import com.galou.mynews.models.ArticleTopStories;
import com.galou.mynews.models.SectionMostPopular;
import com.galou.mynews.models.SectionTopStories;

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
 * Created by galou on 2019-04-03
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleListPresenterUnitTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private ArticleListContract.View articleListView;
    @Mock
    private View view;

    @Mock
    private ArticleTopStories articleTopStories;
    @Mock
    private ArticleMostPopular articleMostPopular;

    private ArticleListPresenter presenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new ArticleListPresenter(articleListView);

        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void createPresenter_setPresenterToView(){

        verify(articleListView).setPresenter(presenter);
    }

    @Test
    public void onConnectionFailed_showSnackBar(){
        presenter.getArticlesFromNYT("test");

        verify(articleListView).showErrorMessage();
    }

    @Test
    public void onConnectionSucceed_showArticlesMostPopular(){
        presenter.getArticlesFromNYT("mostpopular");

        verify(articleListView).setupRecyclerViewMostPopular(presenter.getArticlesMostPopular());
    }

    @Test
    public void onConnectionSucceed_showArticlesTopStories(){
        presenter.getArticlesFromNYT("home");

        verify(articleListView).setupRecyclerViewTopStories(presenter.getArticlesTopStories());
    }

    @Test
    public void onConnectionSucceed_showArticlesSports(){
        presenter.getArticlesFromNYT("sports");

        verify(articleListView).setupRecyclerViewTopStories(presenter.getArticlesTopStories());
    }

    @Test
    public void fetchAPIMostPopular_getListArticles() {
        Observable<SectionMostPopular> observable = ApiStreams.streamsFetchMostPopSection();
        TestObserver<SectionMostPopular> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        SectionMostPopular sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResults().isEmpty());

    }

    @Test
    public void fetchAPITopStories_getListArticles() {
        Observable<SectionTopStories> observable = ApiStreams.streamFetchTopStories("home");
        TestObserver<SectionTopStories> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        SectionTopStories sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResults().isEmpty());

    }

    @Test
    public void fetchAPISports_getListArticles() {
        Observable<SectionTopStories> observable = ApiStreams.streamFetchTopStories("sports");
        TestObserver<SectionTopStories> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        SectionTopStories sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResults().isEmpty());

    }

    @Test
    public void emptyListTopStoriesFetched_sendMessageView() {
        List<ArticleTopStories> articlesList = new ArrayList<>();
        presenter.sendListArticleTopStories(articlesList);

        verify(articleListView).showEmptyNewsMessage();
    }

    @Test
    public void emptyListMostPopularFetched_sendMessageView() {
        List<ArticleMostPopular> articlesList = new ArrayList<>();
        presenter.sendListArticleMostPopular(articlesList);

        verify(articleListView).showEmptyNewsMessage();
    }

    @Test
    public void getUrlArticleTopStories_sentToView(){
        String url = "http://test";
        when(articleTopStories.getUrl()).thenReturn(url);
        presenter.getUrlArticleTopStories(articleTopStories, view);

        verify(articleListView).showDetailsArticle(url, view);
    }

    @Test
    public void getUrlArticleMostPopular_sentToView(){
        String url = "http://test";
        when(articleMostPopular.getUrl()).thenReturn(url);
        presenter.getUrlArticleMostPopular(articleMostPopular, view);

        verify(articleListView).showDetailsArticle(url, view);
    }

    @AfterClass
    public static void after(){
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
    }
}
