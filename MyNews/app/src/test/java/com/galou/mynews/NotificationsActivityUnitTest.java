package com.galou.mynews;

import com.galou.mynews.controllers.activities.NotificationsActivity;
import com.galou.mynews.controllers.fragments.NotificationsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by galou on 2019-03-19
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationsActivityUnitTest {

    private NotificationsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(NotificationsActivity.class).create().resume().get();
    }

    @Test
    public void activityShouldNotBeNull() throws Exception{
        assertNotNull(activity);
    }

    @Test
    public void notificationsFragmentShouldBeVisible() throws Exception {
        assertEquals(activity.getSupportFragmentManager().findFragmentById(R.id.notifications_activity_frame_layout).getClass().getName(), NotificationsFragment.class.getName());
    }
}
