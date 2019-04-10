package com.galou.mynews.resultsSearch;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.galou.mynews.R;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;
import com.galou.mynews.searchNotification.SearchView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsSearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private ResultsSearchView resultsSearchView;
    private ResultSearchPresenter presenter;
    private SectionSearch searchQuery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        ButterKnife.bind(this);
        configureToolbar();
        getSearchQuery();
        configureAndShowFragment();
        setPresenter();
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void configureAndShowFragment(){
        resultsSearchView = (ResultsSearchView) getSupportFragmentManager().findFragmentById(R.id.results_activity_frame_layout);
        if (resultsSearchView == null){
            resultsSearchView = new ResultsSearchView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.results_activity_frame_layout, resultsSearchView)
                    .commit();
        }

    }

    private void setPresenter(){
        presenter = new ResultSearchPresenter(resultsSearchView, searchQuery);
    }

    private void getSearchQuery(){
        String json = getIntent().getStringExtra(SearchView.EXTRA_KEY_ARTICLE);
        Gson gson = new Gson();
        if(json != null){
            searchQuery = gson.fromJson(json, SectionSearch.class);
        } else {
            searchQuery = null;
        }

    }


}
