package com.galou.mynews.consultArticles;


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
import com.galou.mynews.models.ArticleMostPopular;
import com.galou.mynews.models.ArticleTopStories;
import com.galou.mynews.utils.ItemClickSupport;
import com.galou.mynews.webViewArticle.WebViewArticleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListView extends Fragment implements ArticleListContract.View {

    public static final String KEY_SECTION = "section";

    private String section;

    private ArticleListContract.Presenter presenter;

    private List<ArticleMostPopular> articlesMostPopular;
    private List<ArticleTopStories> articleTopStories;
    private ArticleMostPopularAdapter adapterMostPopular;
    private ArticleTopStoriesAdapter adapterTopStories;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.article_list_view_root_view) CoordinatorLayout rootView;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout refreshLayout;


    public ArticleListView() {}

    // -----------------
    // CREATE DIFFERENT INSTANCE
    // -----------------

    public static ArticleListView newInstance(String section){
        ArticleListView view = new ArticleListView();
        Bundle args = new Bundle();
        args.putString(KEY_SECTION, section);
        view.setArguments(args);
        return (view);

    }

    // -----------------
    // CONFIGURATION
    // -----------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list_view, container, false);
        ButterKnife.bind(this, view);
        this.section = getArguments().getString(KEY_SECTION, "");
        this.configureRecyclerView();
        presenter.getArticlesFromNYT(this.section);
        this.configureSwipeRefreshLayout();
        return (view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disposeWhenDestroy();
    }


    public String getSection() {
        return section;
    }

    @Override
    public void setPresenter(ArticleListContract.Presenter presenter) {
        this.presenter = presenter;

    }

    // -----------------
    // SETUP AND SHOW RECYCLER VIEW
    // -----------------

    private void configureRecyclerView(){
        if(section.equals("mostpopular")) {
            articlesMostPopular = new ArrayList<>();
            adapterMostPopular = new ArticleMostPopularAdapter(articlesMostPopular, Glide.with(this));
            recyclerView.setAdapter(adapterMostPopular);
        } else {
            articleTopStories = new ArrayList<>();
            adapterTopStories = new ArticleTopStoriesAdapter(articleTopStories, Glide.with(this));
            recyclerView.setAdapter(adapterTopStories);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setupRecyclerViewMostPopular(List<ArticleMostPopular> articles) {
        refreshLayout.setRefreshing(false);
        this.articlesMostPopular.addAll(articles);
        adapterMostPopular.update(this.articlesMostPopular);
        configureOnClickRecyclerViewMostPopular();

    }

    @Override
    public void setupRecyclerViewTopStories(List<ArticleTopStories> articles) {
        refreshLayout.setRefreshing(false);
        this.articleTopStories.addAll(articles);
        adapterTopStories.update(this.articleTopStories);
        configureOnClickRecyclerViewTopStories();

    }

    private void configureOnClickRecyclerViewMostPopular(){
        ItemClickSupport.addTo(recyclerView, R.layout.article_item_view)
                .setOnItemClickListener((recyclerView, position, v)
                        -> presenter.getUrlArticleMostPopular(adapterMostPopular.getArticle(position)));

    }

    private void configureOnClickRecyclerViewTopStories(){
        ItemClickSupport.addTo(recyclerView, R.layout.article_item_view)
                .setOnItemClickListener((recyclerView, position, v)
                        -> presenter.getUrlArticleTopStories(adapterTopStories.getArticle(position)));

    }

    // -----------------
    // REFRESH
    // -----------------

    private void configureSwipeRefreshLayout(){
        refreshLayout.setOnRefreshListener(() -> presenter.getArticlesFromNYT(section));
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
    // SHOW WEB VIEW
    // -----------------

    @Override
    public void showDetailsArticle(String url) {
        Intent intent = new Intent(getContext(), WebViewArticleActivity.class);
        intent.putExtra(WebViewArticleActivity.KEY_URL, url);
        startActivity(intent);

    }

}
