package com.galou.mynews.resultsSearch;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.galou.mynews.R;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.utils.ItemClickSupport;
import com.galou.mynews.webViewArticle.WebViewArticleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsSearchView extends Fragment implements ResultSearchContract.View {

    @BindView(R.id.result_search_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.result_search_view_root_view) CoordinatorLayout rootView;
    @BindView(R.id.result_search_swipe_refresh) SwipeRefreshLayout refreshLayout;

    private List<ArticleSearch> articles;
    private ResultSearchContract.Presenter presenter;
    private ArticleSearchAdapter adapter;


    public ResultsSearchView() {}

    // -----------------
    // CONFIGURATION START
    // -----------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_search_view, container, false);
        ButterKnife.bind(this, view);
        configureRecyclerView();
        presenter.getArticles();
        configureSwipeRefreshLayout();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disposeWhenDestroy();
    }

    @Override
    public void setPresenter(ResultSearchContract.Presenter presenter) {
        this.presenter = presenter;

    }

    private void configureSwipeRefreshLayout(){
        refreshLayout.setOnRefreshListener(() -> presenter.getArticles());
    }

    // -----------------
    // SETUP AND SHOW RECYCLER VIEW
    // -----------------

    private void configureRecyclerView(){
        articles = new ArrayList<>();
        adapter = new ArticleSearchAdapter(this.articles, Glide.with(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void showArticles(List<ArticleSearch> articles) {
        refreshLayout.setRefreshing(false);
        this.articles.addAll(articles);
        adapter.update(articles);
        configureOnClickRecyclerView();

    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.article_item_view)
                .setOnItemClickListener((recyclerView, position, v)
                        -> presenter.getUrlArticle(adapter.getArticle(position)));

    }

    // -----------------
    // ERROR MESSAGE
    // -----------------

    @Override
    public void showErrorMessage() {
        Snackbar snackbar = Snackbar.make(rootView, R.string.connection_failed, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void showEmptyNewsMessage() {
        Snackbar snackbar = Snackbar.make(rootView, R.string.no_news, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    // -----------------
    // START WEBVIEW
    // -----------------

    @Override
    public void showDetailsArticle(String url) {
        Intent intent = new Intent(getContext(), WebViewArticleActivity.class);
        intent.putExtra(WebViewArticleActivity.KEY_URL, url);
        startActivity(intent);

    }

}
