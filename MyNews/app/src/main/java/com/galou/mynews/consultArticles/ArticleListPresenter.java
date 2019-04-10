package com.galou.mynews.consultArticles;

import android.support.annotation.NonNull;

import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleMostPopular;
import com.galou.mynews.models.ArticleTopStories;
import com.galou.mynews.models.SectionMostPopular;
import com.galou.mynews.models.SectionTopStories;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by galou on 2019-04-03
 */
public class ArticleListPresenter implements ArticleListContract.Presenter{

    private ArticleListContract.View articleListView;
    private List<ArticleTopStories> articlesTopStories;
    private List<ArticleMostPopular> articlesMostPopular;

    private Disposable disposable;

    public ArticleListPresenter(@NonNull ArticleListContract.View articleListView) {
        this.articleListView = articleListView;
        this.articleListView.setPresenter(this);


    }

    // -----------------
    // API REQUEST
    // -----------------

    @Override
    public void getArticlesFromNYT(String section) {
        if(section.equals("mostpopular")) {
            this.disposable = ApiStreams.streamsFetchMostPopSection().subscribeWith(getObserverMostPopular());
        } else {
            this.disposable = ApiStreams.streamFetchTopStories(section).subscribeWith(getObserverTopStories());
        }
    }

    @Override
    public void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private DisposableObserver<SectionMostPopular> getObserverMostPopular(){
        return new DisposableObserver<SectionMostPopular>() {
            @Override
            public void onNext(SectionMostPopular section) {
                sendListArticleMostPopular(section.getResults());

            }

            @Override
            public void onError(Throwable e) {
                sendErrorToView(e);

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<SectionTopStories> getObserverTopStories(){
        return new DisposableObserver<SectionTopStories>() {
            @Override
            public void onNext(SectionTopStories section) {
                sendListArticleTopStories(section.getResults());

            }

            @Override
            public void onError(Throwable e) {
                sendErrorToView(e);

            }

            @Override
            public void onComplete() {

            }
        };
    }

    // -----------------
    // SEND INFOS VIEW
    // -----------------

    private void sendListArticleMostPopular(List<ArticleMostPopular> articles) {
        this.articlesMostPopular = articles;
        if (articlesMostPopular.size() < 0) {
            articleListView.showEmptyNewsMessage();

        } else {
            articleListView.setupRecyclerViewMostPopular(articlesMostPopular);
        }
    }

    private void sendErrorToView(Throwable e){
        articleListView.showErrorMessage();
    }

    private void sendListArticleTopStories(List<ArticleTopStories> articles){
        this.articlesTopStories = articles;
        if (articlesTopStories.size() < 0) {
            articleListView.showEmptyNewsMessage();
        } else {
            articleListView.setupRecyclerViewTopStories(articlesTopStories);
        }
    }

    // -----------------
    // GET DETAILS FOR WEBVIEW
    // -----------------

    @Override
    public void getUrlArticleMostPopular(ArticleMostPopular article) {
        articleListView.showDetailsArticle(article.getUrl());
    }

    @Override
    public void getUrlArticleTopStories(ArticleTopStories article) {
        articleListView.showDetailsArticle(article.getUrl());

    }

    // -----------------
    // FOR TESTING
    // -----------------

    protected List<ArticleMostPopular> getArticlesMostPopular(){
        return articlesMostPopular;

    }

    protected List<ArticleTopStories> getArticlesTopStories(){
        return articlesTopStories;

    }


}
