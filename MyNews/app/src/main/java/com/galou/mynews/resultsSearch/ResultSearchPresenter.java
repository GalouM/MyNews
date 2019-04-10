package com.galou.mynews.resultsSearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by galou on 2019-04-09
 */
public class ResultSearchPresenter implements ResultSearchContract.Presenter {

    private ResultSearchContract.View resultView;
    private SectionSearch searchQuery;
    private String beginDate;
    private String endDate;
    private String querySections;
    private String queryTerms;
    private List<ArticleSearch> articles;

    private Disposable disposable;

    public ResultSearchPresenter(@NonNull ResultSearchContract.View resultView, @Nullable String beginDate,
                                 @Nullable String endDate, String querySection, String queryTerms){
        this.resultView = resultView;
        this.resultView.setPresenter(this);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.querySections = querySection;
        this.queryTerms = queryTerms;

    }

    // -----------------
    // API REQUEST
    // -----------------

    @Override
    public void getArticles() {
        this.disposable = ApiStreams.streamFetchSearch(beginDate, endDate, querySections, queryTerms).subscribeWith(getObserverSearch());

    }

    private DisposableObserver<SectionSearch> getObserverSearch(){
        return new DisposableObserver<SectionSearch>() {
            @Override
            public void onNext(SectionSearch section) {
                sendArticlesToView(section.getResponse().getDocs());

            }

            @Override
            public void onError(Throwable e) {
                sendErrorToView();

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }

    // -----------------
    // GET DETAILS FOR WEBVIEW
    // -----------------


    @Override
    public void getUrlArticle(ArticleSearch article) {
        resultView.showDetailsArticle(article.getWebUrl());

    }

    // -----------------
    // SEND INFOS VIEW
    // -----------------

    private void sendArticlesToView(List<ArticleSearch> articleSearches){
        this.articles = articleSearches;
        if(articles.size() > 0) {
            resultView.showArticles(articles);
        } else {
            resultView.showEmptyNewsMessage();
        }
    }

    private void sendErrorToView(){
        resultView.showErrorMessage();
    }
}
