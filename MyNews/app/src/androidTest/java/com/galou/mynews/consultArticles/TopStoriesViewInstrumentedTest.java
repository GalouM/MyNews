package com.galou.mynews.consultArticles;

import android.support.test.runner.AndroidJUnit4;

import com.galou.mynews.models.Section;
import com.galou.mynews.models.ApiStreams;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertFalse;

/**
 * Created by galou on 2019-03-30
 */
@RunWith(AndroidJUnit4.class)
public class TopStoriesViewInstrumentedTest {
    @Test
    public void fetchAPIGetListArticles() throws Exception {
        Observable<Section> observable = ApiStreams.streamFetchTopStories("home");
        TestObserver<Section> testObserver = new TestObserver<>();
        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        Section sectionFetched = testObserver.values().get(0);

        assertFalse(sectionFetched.getResults().isEmpty());

    }
}
