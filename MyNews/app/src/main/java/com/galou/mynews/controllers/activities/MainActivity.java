package com.galou.mynews.controllers.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.galou.mynews.R;
import com.galou.mynews.controllers.adapters.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.main_activity_viewpager) ViewPager viewpager;
    @BindView(R.id.main_activity_tabs) TabLayout tabLayout;
    @BindView(R.id.main_activity_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureViewPagerAndTabs();
        this.configureToolBar();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
}
