package com.galou.mynews;

import android.support.v4.view.ViewPager;
import android.text.Layout;

import com.galou.mynews.controllers.activities.MainActivity;
import com.galou.mynews.controllers.adapters.PageAdapter;
import com.galou.mynews.controllers.fragments.TopStoriesFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.fakes.RoboMenuItem;

import static org.junit.Assert.*;


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
    }

    @Test
    public void clickNavDrawerMostPop()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_pop));
        assertEquals(pageAdapter.getPageTitle(viewPager.getCurrentItem()), "MOST POPULAR");
    }

    @Test
    public void clickNavDrawerSport()throws Exception {
        activity.onNavigationItemSelected(new RoboMenuItem(R.id.main_activity_drawer_sport));
        assertEquals(pageAdapter.getPageTitle(viewPager.getCurrentItem()), "SPORT");
    }

}