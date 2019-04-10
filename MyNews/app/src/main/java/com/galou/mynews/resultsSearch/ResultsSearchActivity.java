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

import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_BEGIN_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_END_DATE;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;

public class ResultsSearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private ResultsSearchView resultsSearchView;
    private ResultSearchPresenter presenter;
    private SectionSearch searchQuery;

    private String beginDate;
    private String endDate;
    private String querySections;
    private String queryTerms;



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

    // -----------------
    // CONFIGURATION
    // -----------------

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
        presenter = new ResultSearchPresenter(resultsSearchView, beginDate, endDate, querySections, queryTerms);
    }

    // -----------------
    // GET DATA FROM INTENT
    // -----------------

    private void getSearchQuery(){
        Bundle bundle = getIntent().getExtras();
        beginDate = bundle.getString(BUNDLE_KEY_BEGIN_DATE);
        endDate = bundle.getString(BUNDLE_KEY_END_DATE);
        querySections = bundle.getString(BUNDLE_KEY_QUERY_SECTIONS);
        queryTerms = bundle.getString(BUNDLE_KEY_QUERY_TERM);

    }


}
