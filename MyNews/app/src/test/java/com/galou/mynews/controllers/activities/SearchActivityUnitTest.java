package com.galou.mynews.controllers.activities;

import com.galou.mynews.R;
import com.galou.mynews.searchNotification.SearchActivity;
import com.galou.mynews.searchNotification.SearchView;

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
        assertEquals(activity.getSupportFragmentManager().findFragmentById(R.id.search_activity_frame_layout).getClass().getName(), SearchView.class.getName());
    }

}
