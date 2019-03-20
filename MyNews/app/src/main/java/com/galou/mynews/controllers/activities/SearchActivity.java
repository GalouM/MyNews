package com.galou.mynews.controllers.activities;

import android.os.Bundle;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.SearchFragment;

public class SearchActivity extends BaseActivity {


    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureAndShowSearchFragment();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_search;
    }

    // -------------------
    // CONFIGURATION
    // -------------------

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
