package com.galou.mynews.controllers.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.BaseFragmentSearch;
import com.galou.mynews.controllers.fragments.SearchFragment;

import java.util.List;

public class SearchActivity extends BaseActivity implements BaseFragmentSearch.OnButtonClickedListener  {


    private SearchFragment searchFragment;

    //for data
    private String queryTerm;
    private List<String> querySection;
    private String queryBeginDate;
    private String queryEndDate;

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

    // -------------------
    // SETTER
    // -------------------


    public void setQueryTerm(String queryTerm) {
        this.queryTerm = queryTerm;
    }

    public void setQuerySection(List<String> querySection) {
        this.querySection = querySection;
    }

    public void setQueryBeginDate(String queryBeginDate) {
        this.queryBeginDate = queryBeginDate;
    }

    public void setQueryEndDate(String queryEndDate) {
        this.queryEndDate = queryEndDate;
    }

    // --------------
    // CallBack
    // --------------

    @Override
    public void onButtonClicked(View view) {
        String value = queryTerm + "/" + queryBeginDate + "/" + queryEndDate + "/" + querySection;
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();

    }
}
