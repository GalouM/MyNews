package com.galou.mynews.searchNotification;

import com.galou.mynews.R;
import com.galou.mynews.searchNotification.NotificationsActivity;
import com.galou.mynews.searchNotification.NotificationsView;

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
        assertEquals(activity.getSupportFragmentManager().findFragmentById(R.id.notifications_activity_frame_layout).getClass().getName(), NotificationsView.class.getName());
    }

}
