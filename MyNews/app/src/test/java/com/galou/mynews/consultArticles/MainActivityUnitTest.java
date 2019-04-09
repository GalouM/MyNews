package com.galou.mynews.consultArticles;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.galou.mynews.R;
import com.galou.mynews.searchNotification.NotificationsActivity;
import com.galou.mynews.searchNotification.SearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.fakes.RoboMenuItem;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {

    private MainActivity activity;
    private ViewPager viewPager;

    @Mock
    private PageAdapter pageAdapter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        viewPager = (ViewPager) activity.getWindow().findViewById(R.id.main_activity_viewpager);
    }

    @Test
    public void activityShouldNotBeNull()throws Exception{
        assertNotNull(activity);
    }

    @Test
    public void viewPagerIsShown() throws Exception {
        assertNotNull(activity.findViewById(R.id.main_activity_viewpager));
    }

    @Test
    public void tabLayoutIsShown() throws Exception {
        assertNotNull(activity.findViewById(R.id.main_activity_tabs));
    }

    @Test
    public void navDrawerIsShown() throws Exception {
        assertNotNull(activity.findViewById(R.id.main_activity_nav_view));
    }

    @Test
    public void clickNavDrawerTopStory()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_top));

        doReturn("TOP STORIES").when(pageAdapter).getPageTitle(viewPager.getCurrentItem());
    }

    @Test
    public void clickNavDrawerMostPop()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_pop));

        doReturn("MOST POPULAR").when(pageAdapter).getPageTitle(viewPager.getCurrentItem());
    }

    @Test
    public void clickNavDrawerSport()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_sport));

        doReturn("SPORTS").when(pageAdapter).getPageTitle(viewPager.getCurrentItem());
    }

    @Test
    public void viewPagerFalseId()throws Exception {
        int id = 50;

        doReturn(null).when(pageAdapter).getPageTitle(viewPager.getCurrentItem());
    }

    @Test
    public void clickNavDrawerSearch() throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_search));
        Intent intent = shadowOf(activity).getNextStartedActivity();

        assertEquals(SearchActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void clickNavDrawerNotifications() throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_notification));
        Intent intent = shadowOf(activity).getNextStartedActivity();

        assertEquals(NotificationsActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void clickToolbarSearch() throws Exception {
        activity.onOptionsItemSelected(new RoboMenuItem(R.id.menu_main_activity_search));
        Intent intent = shadowOf(activity).getNextStartedActivity();

        assertEquals(SearchActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void clickToolbarNotifications() throws Exception {
        activity.onOptionsItemSelected(new RoboMenuItem(R.id.menu_main_activity_notifications));
        Intent intent = shadowOf(activity).getNextStartedActivity();

        assertEquals(NotificationsActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void onPressBack() throws Exception{
        activity.onBackPressed();

        assertTrue(activity.isFinishing());

    }

}