package com.galou.mynews.consultArticles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.galou.mynews.models.ArticleMostPopular;
import com.galou.mynews.models.ArticleTopStories;
import com.galou.mynews.models.SectionMostPopular;
import com.galou.mynews.models.SectionTopStories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by galou on 2019-04-03
 */
@RunWith(RobolectricTestRunner.class)
public class ArticleListViewUnitTest {

    private ArticleMostPopularAdapter adapterMostPopular;
    private ArticleTopStoriesAdapter adapterTopStories;
    private List<ArticleMostPopular> articleMostPopulars;
    private List<ArticleTopStories> articleTopStories;
    private MainActivity activity;
    // views
    private TextView title;
    private TextView date;
    private TextView sectionDisplay;
    private RecyclerView recyclerView;

    @Mock
    private SectionMostPopular sectionMostPopular;
    @Mock
    private SectionTopStories sectionTopStories;

    private Context context;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(MainActivity.class).create()
                .start()
                .resume()
                .get();
        context = activity.getBaseContext();

    }

    @Test
    public void numberItemInRecyclerViewMostPopular_equalToNumberArticleFromAPI(){
        ArticleMostPopular article1 = new ArticleMostPopular();
        ArticleMostPopular article2 = new ArticleMostPopular();
        List<ArticleMostPopular> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        when(sectionMostPopular.getResults()).thenReturn(articles);
        Observable<SectionMostPopular> observable = Observable.just(sectionMostPopular);
        TestObserver<SectionMostPopular> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .awaitTerminalEvent();

        SectionMostPopular sectionFetched = testObserver.values().get(0);

        articleMostPopulars = sectionFetched.getResults();

        adapterMostPopular = new ArticleMostPopularAdapter(articleMostPopulars, Glide.with(context));

        assertEquals(sectionFetched.getResults().size(), adapterMostPopular.getItemCount());

    }

    @Test
    public void numberItemInRecyclerViewTopStories_equalToNumberArticleFromAPI(){
        ArticleTopStories article1 = new ArticleTopStories();
        ArticleTopStories article2 = new ArticleTopStories();
        List<ArticleTopStories> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        when(sectionTopStories.getResults()).thenReturn(articles);
        Observable<SectionTopStories> observable = Observable.just(sectionTopStories);
        TestObserver<SectionTopStories> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .awaitTerminalEvent();

        SectionTopStories sectionFetched = testObserver.values().get(0);

        articleTopStories = sectionFetched.getResults();

        adapterTopStories = new ArticleTopStoriesAdapter(articleTopStories, Glide.with(context));

        assertEquals(sectionFetched.getResults().size(), adapterTopStories.getItemCount());

    }

}
