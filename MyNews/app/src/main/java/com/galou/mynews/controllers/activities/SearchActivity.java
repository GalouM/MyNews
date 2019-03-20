package com.galou.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureAndShowSearchFragment();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureAndShowSearchFragment(){
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_activity_frame_layout);
        if (searchFragment == null){
            searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.search_activity_frame_layout, searchFragment)
                    .commit();
        }

    }
}
