package com.galou.mynews;

import com.galou.mynews.controllers.activities.SearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

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
    public void activityShouldNotBeNull()throws Exception{
        assertNotNull(activity);
    }
}
