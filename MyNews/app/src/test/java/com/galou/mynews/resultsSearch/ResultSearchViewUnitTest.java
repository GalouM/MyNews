package com.galou.mynews.resultsSearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.galou.mynews.R;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.ResponseSearch;
import com.galou.mynews.models.SectionSearch;
import com.galou.mynews.utils.SwipeRefreshLayoutBottom;
import com.galou.mynews.utils.TextUtil;
import com.galou.mynews.webViewArticle.WebViewArticleActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_BEGIN_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_END_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-04-10
 */
@RunWith(RobolectricTestRunner.class)
public class ResultSearchViewUnitTest {

    private ResultsSearchActivity activity;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private SwipeRefreshLayoutBottom refreshLayoutBottom;
    private ArticleSearchAdapter adapter;
    private Context context;
    private List<ArticleSearch> listArticles;
    private ArticleSearch article1;
    private ArticleSearch article2;

    @Mock
    private ResultSearchContract.Presenter presenter;
    @Mock
    private SectionSearch section;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        String beginDate = "20170101";
        String endDate = "20190110";
        String terms = TextUtil.convertQueryTermForAPI("usa canada");
        List<String> list = new ArrayList<>();
        list.add("Business");
        list.add("Travel");
        String sections = TextUtil.convertListInStringForAPI(list);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_BEGIN_DATE, beginDate);
        bundle.putString(BUNDLE_KEY_END_DATE, endDate);
        bundle.putString(BUNDLE_KEY_QUERY_SECTIONS, sections);
        bundle.putString(BUNDLE_KEY_QUERY_TERM, terms);

        Intent intent = new Intent(RuntimeEnvironment.application, ResultsSearchActivity.class);
        intent.putExtras(bundle);
        activity = Robolectric.buildActivity(ResultsSearchActivity.class, intent).create().resume().get();
        recyclerView = (RecyclerView) activity.findViewById(R.id.result_search_recycler_view);
        refreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.result_search_swipe_refresh);
        refreshLayoutBottom = (SwipeRefreshLayoutBottom) activity.findViewById(R.id.result_search_swipe_refresh_bottom);
        adapter = (ArticleSearchAdapter) recyclerView.getAdapter();

        context = activity.getBaseContext();

        listArticles = new ArrayList<>();
        listArticles.add(article1);
        listArticles.add(article2);
    }

    @Test
    public void recyclerView_isVisible() throws Exception{
        assertNotNull(recyclerView);
    }

    @Test
    public void swipeLayout_isVisible() throws Exception{
        assertNotNull(refreshLayout);
    }

    @Test
    public void swipeLayoutBottom_isVisible() throws Exception{
        assertNotNull(refreshLayoutBottom);
    }

    @Test
    public void recyclerViewAdapter_isSet() throws Exception{
        assertNotNull(adapter);

    }

    @Test
    public void numberItemInRecyclerView_equalToNumberArticleFromAPI() throws Exception{
        ArticleSearch article1 = new ArticleSearch();
        ArticleSearch article2 = new ArticleSearch();
        List<ArticleSearch> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        ResponseSearch responseSearch = new ResponseSearch();
        responseSearch.setDocs(articles);
        when(section.getResponse()).thenReturn(responseSearch);
        Observable<SectionSearch> observable = Observable.just(section);
        TestObserver<SectionSearch> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .awaitTerminalEvent();

        SectionSearch sectionFetched = testObserver.values().get(0);

        List<ArticleSearch> listArticles = sectionFetched.getResponse().getDocs();

        ArticleSearchAdapter adapter = new ArticleSearchAdapter(listArticles, Glide.with(context));

        assertEquals(sectionFetched.getResponse().getDocs().size(), adapter.getItemCount());

    }

    @Test
    public void titleDisplay_sameTitleArticle() throws Exception{
        //recyclerView.measure(0,0);
        //recyclerView.layout(0,0,100,1000);
        //TextView textView = (TextView)recyclerView.getChildAt(0).findViewById();
        //recyclerView.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.recycler_view_title_article);
        //recyclerView.measure(0,0);
        //recyclerView.layout(0,0,100,1000);
        //String title = adapter.getArticle(0).getHeadline().getPrintHeadline();
        //presenter.getArticles();

        //assertNotNull(adapter.getArticle(0));
        assertNotNull(adapter);

    }
}
