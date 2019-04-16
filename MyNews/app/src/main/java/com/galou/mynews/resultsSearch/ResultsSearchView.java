package com.galou.mynews.resultsSearch;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.library21.custom.SwipeRefreshLayoutBottom;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
public class ResultsSearchView extends Fragment implements ResultSearchContract.View{

    @BindView(R.id.result_search_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.result_search_view_root_view) CoordinatorLayout rootView;
    @BindView(R.id.result_search_swipe_refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.result_search_swipe_refresh_bottom) SwipeRefreshLayoutBottom refreshLayoutBottom;
    @BindView(R.id.result_view_frame_layout) FrameLayout frameLayout;

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
        configureForeground();
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
        refreshLayout.setOnRefreshListener(this::onRefreshLayout);
        refreshLayoutBottom.setOnRefreshListener(this::onRefreshLayoutBottom);
    }

    private void configureForeground(){
        frameLayout.setForeground(new ColorDrawable(Color.BLACK));
        frameLayout.getForeground().setAlpha(0);
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
        turnOffRefreshing();
        this.articles = articles;
        adapter.update(this.articles);
        configureOnClickRecyclerView();

    }

    @Override
    public void showNextArticles(List<ArticleSearch> articles) {
        turnOffRefreshing();
        recyclerView.scrollToPosition(adapter.getItemCount());
        this.articles.addAll(articles);
        adapter.addArticlesUpdate(articles);
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
        turnOffRefreshing();
        Snackbar snackbar = Snackbar.make(rootView, R.string.connection_failed, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.hide_button_snackbar, view -> snackbar.dismiss());
        snackbar.show();

    }

    @Override
    public void showEmptyNewsMessage() {
        Snackbar snackbar = Snackbar.make(rootView, R.string.no_news, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.hide_button_snackbar, view -> snackbar.dismiss());
        snackbar.show();

    }

    @Override
    public void showNoMoreNews() {
        turnOffRefreshing();
        Snackbar snackbar = Snackbar.make(rootView, R.string.no_more_news, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.hide_button_snackbar, view -> snackbar.dismiss());
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

    // -----------------
    // REFRESH BOTTOM SCROLL
    // -----------------

    private void onRefreshLayoutBottom() {
        frameLayout.getForeground().setAlpha(50);
        refreshLayoutBottom.setRefreshing(true);
        presenter.getNextArticles();

    }

    private void onRefreshLayout(){
        frameLayout.getForeground().setAlpha(50);
        presenter.getArticles();
    }

    private void turnOffRefreshing(){
        frameLayout.getForeground().setAlpha(0);
        refreshLayoutBottom.setRefreshing(false);
        refreshLayout.setRefreshing(false);
    }
}
