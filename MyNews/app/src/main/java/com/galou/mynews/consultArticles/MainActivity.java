package com.galou.mynews.consultArticles;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.galou.mynews.BuildConfig;
import com.galou.mynews.R;
import com.galou.mynews.searchNotification.NotificationsActivity;
import com.galou.mynews.searchNotification.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String CHANNEL_ID = "channelID";

    // Views
    @BindView(R.id.main_activity_viewpager) ViewPager viewpager;
    @BindView(R.id.main_activity_tabs) TabLayout tabLayout;
    @BindView(R.id.main_activity_drawer) DrawerLayout drawerLayout;
    @BindView(R.id.main_activity_nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.createNotificationChannel();
        this.configureToolbar();
        this.configureViewPagerAndTabs();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    protected void configureToolbar(){
        setSupportActionBar(toolbar);

    }
    private void configureViewPagerAndTabs(){
        viewpager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


    private void configureDrawerLayout(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void configureNavigationView(){
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.main_activity_drawer_top:
                viewpager.setCurrentItem(0);
                break;
            case R.id.main_activity_drawer_pop:
                viewpager.setCurrentItem(1);
                break;
            case R.id.main_activity_drawer_sport:
                viewpager.setCurrentItem(2);
                break;
            case R.id.main_activity_drawer_search:
                this.startSearchActivity();
                break;
            case R.id.main_activity_drawer_notification:
                this.startNotificationsActivity();
                break;
            default:
                 break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        View view = toolbar.findViewById(id);
        switch (id){
            case R.id.menu_main_activity_search:
                startSearchActivityFromToolBar(view);
                return true;
            case R.id.menu_main_activity_notifications:
                this.startNotificationsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // -----------------
    // START ACTIVITIES
    // -----------------

    private void startSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

    }

    private void startSearchActivityFromToolBar(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.animation_main_to_search_zoom));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }



    private void startNotificationsActivity(){
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);

    }


    // -----------------
    // CREATE NOTIFICATION CHANNEL
    // -----------------

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "My News channel";
            String description = "Notification channel for My News";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
