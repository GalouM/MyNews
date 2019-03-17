package com.galou.mynews.controllers.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.galou.mynews.R;
import com.galou.mynews.controllers.adapters.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // Views
    @BindView(R.id.main_activity_viewpager) ViewPager viewpager;
    @BindView(R.id.main_activity_tabs) TabLayout tabLayout;
    @BindView(R.id.main_activity_toolbar) Toolbar toolbar;
    @BindView(R.id.main_activity_drawer) DrawerLayout drawerLayout;
    @BindView(R.id.main_activity_nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureViewPagerAndTabs();
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    // -------------------
    // CONFIGURATION
    // -------------------
    private void configureViewPagerAndTabs(){
        viewpager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private void configureToolBar(){
        setSupportActionBar(toolbar);
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
            default:
                 break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
