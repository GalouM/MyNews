package com.galou.mynews.searchNotification;

import com.galou.mynews.R;

public class SearchActivity extends BaseActivity{


    private SearchView searchView;
    private SearchPresenter presenter;


    @Override
    protected int getActivityLayout() {
        return R.layout.activity_search;
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    @Override
    protected void configureAndShowFragment(){
        searchView = (SearchView) getSupportFragmentManager().findFragmentById(R.id.search_activity_frame_layout);
        if (searchView == null){
            searchView = new SearchView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.search_activity_frame_layout, searchView)
                    .commit();
        }

    }

    @Override
    protected void setPresenter() {
        presenter = new SearchPresenter(searchView);

    }

}
