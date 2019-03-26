package com.galou.mynews.controllers.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;

import com.galou.mynews.R;
import com.galou.mynews.controllers.activities.MainActivity;
import com.galou.mynews.controllers.activities.NotificationsActivity;
import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.controllers.adapters.PageAdapter;
import com.galou.mynews.controllers.fragments.MostPopFragment;
import com.galou.mynews.controllers.fragments.SportFragment;
import com.galou.mynews.controllers.fragments.TopStoriesFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.fakes.RoboMenuItem;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {

    private MainActivity activity;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        viewPager = (ViewPager) activity.getWindow().findViewById(R.id.main_activity_viewpager);
        pageAdapter = new PageAdapter(activity.getSupportFragmentManager());
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

        assertEquals(pageAdapter.getPageTitle(viewPager.getCurrentItem()), "TOP STORIES");
        assertEquals(pageAdapter.getItem(viewPager.getCurrentItem()).getClass().getName(), TopStoriesFragment.class.getName());
    }

    @Test
    public void clickNavDrawerMostPop()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_pop));

        assertEquals(pageAdapter.getPageTitle(viewPager.getCurrentItem()), "MOST POPULAR");
        assertEquals(pageAdapter.getItem(viewPager.getCurrentItem()).getClass().getName(), MostPopFragment.class.getName());
    }

    @Test
    public void clickNavDrawerSport()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_sport));

        assertEquals(pageAdapter.getPageTitle(viewPager.getCurrentItem()), "SPORT");
        assertEquals(pageAdapter.getItem(viewPager.getCurrentItem()).getClass().getName(), SportFragment.class.getName());
    }

    @Test
    public void viewPagerFalseId()throws Exception {
        int id = 50;

        assertNull(pageAdapter.getPageTitle(id));
        assertNull(pageAdapter.getItem(id));
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