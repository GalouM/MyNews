package com.galou.mynews;

import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.controllers.fragments.SearchFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by galou on 2019-03-19
 */
@RunWith(RobolectricTestRunner.class)
public class SearchActivityUnitTest {

    private SearchActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
    }

    @Test
    public void activityShouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void searchFragmentShouldBeVisible() throws Exception {
        assertEquals(activity.getSupportFragmentManager().findFragmentById(R.id.search_activity_frame_layout).getClass().getName(), SearchFragment.class.getName());
    }

    @Test
    public void onSearchLaunchShowToast() throws  Exception {
        String[] queryTerm = {"test","test2"};
        List<String> querySections = new ArrayList<>();
        querySections.add("test");
        querySections.add("test2");
        String beginDate = "20190303";
        String endate = "20190304";
        String messageToast = "Query Term: " + Arrays.toString(queryTerm) + "\n"
                + "Begin Date: " + beginDate + "\n"
                + "End Date: " + endate + "\n"
                + "Section: " + querySections;
        activity.onButtonSearchClicked(queryTerm, beginDate, endate, querySections);

        assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);

    }
}
