package com.galou.mynews.consultArticles;

import android.support.test.runner.AndroidJUnit4;

import com.galou.mynews.models.ArticleAdapter;
import com.galou.mynews.models.Article;
import com.galou.mynews.models.Section;
import com.galou.mynews.models.ApiStreams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by galou on 2019-03-30
 */
@RunWith(AndroidJUnit4.class)
public class MostPopularViewInstrumentedTest {
    private ArticleAdapter adapter;
    private List<Article> listArticle;

    @Before
    public void setup(){
        listArticle = new ArrayList<>();
    }

    @Test
    public void fetchAPIGetListArticles() throws Exception {
        Observable<Section> observable = ApiStreams.streamsFetchMostPopSection();
        TestObserver<Section> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        Section sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResults().isEmpty());

    }

    @Test
    public void numberItemInRecyclerViewIEqualToNumberArticleFromAPI(){
        Observable<Section> observable = ApiStreams.streamsFetchMostPopSection();
        TestObserver<Section> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        Section sectionFetched = testObserver.values().get(0);

        listArticle.addAll(sectionFetched.getResults());

        adapter = new ArticleAdapter(listArticle);

        assertEquals(sectionFetched.getResults().size(), adapter.getItemCount());

    }
}
