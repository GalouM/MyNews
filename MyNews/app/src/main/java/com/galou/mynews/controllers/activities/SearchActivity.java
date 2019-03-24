package com.galou.mynews.controllers.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.SearchFragment;

import java.util.List;

public class SearchActivity extends BaseActivity implements SearchFragment.OnButtonClickedListener {


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

    // --------------
    // CallBack
    // --------------

    @Override
    public void onButtonSearchClicked(String queryTerm, @Nullable String queryBeginDate,
                                      @Nullable String queryEndDate, List<String> querySections) {
        String value = "Query Term: " + queryTerm + "\n"
                + "Begin Date: " + queryBeginDate + "\n"
                + "End Date: " + queryEndDate + "\n"
                + "Section: " + querySections;
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();

    }

}
