package com.galou.mynews.resultsSearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;

import com.galou.mynews.BuildConfig;
import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.galou.mynews.utils.NumberUtil.getNumberPages;

/**
 * Created by galou on 2019-04-09
 */
public class ResultSearchPresenter implements ResultSearchContract.Presenter {

    private ResultSearchContract.View resultView;
    private String beginDate;
    private String endDate;
    private String querySections;
    private String queryTerms;
    private List<ArticleSearch> articles;

    private int pageNumber;
    private int totalNumberPages;

    private Disposable disposable;

    // FOR TESTING
    @VisibleForTesting
    protected CountingIdlingResource espressoTestIdlingResource;

    public ResultSearchPresenter(@NonNull ResultSearchContract.View resultView, @Nullable String beginDate,
                                 @Nullable String endDate, String querySection, String queryTerms){
        this.resultView = resultView;
        this.resultView.setPresenter(this);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.querySections = querySection;
        this.queryTerms = queryTerms;

        this.configureEspressoIdlingResource();

    }

    // -----------------
    // API REQUEST
    // -----------------

    @Override
    public void getArticles() {
        pageNumber = 0;
        this.incrementIdleResource();
        this.disposable = ApiStreams.streamFetchSearch(beginDate, endDate, querySections, queryTerms, 0).subscribeWith(getObserverSearch());

    }

    @Override
    public void getNextArticles() {
        if (pageNumber < totalNumberPages) {
            pageNumber +=1;
            this.incrementIdleResource();
            this.disposable = ApiStreams.streamFetchSearch(beginDate, endDate, querySections, queryTerms, pageNumber).subscribeWith(getObserverSearch());
        } else {
            resultView.showNoMoreNews();
        }

    }

    private DisposableObserver<SectionSearch> getObserverSearch(){
        return new DisposableObserver<SectionSearch>() {
            @Override
            public void onNext(SectionSearch section) {
                sendArticlesToView(section);
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

    private void setTotalPageNumber(int numberArticles){
        totalNumberPages = getNumberPages(numberArticles);
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

    private void sendArticlesToView(SectionSearch section){
        this.decrementIdleResource();
        this.articles = section.getResponse().getDocs();
        if(articles.size() > 0) {
            if(pageNumber == 0) {
                resultView.showArticles(articles);
                setTotalPageNumber(section.getResponse().getMeta().getHits());
            } else {
                resultView.showNextArticles(articles);
            }
        } else {
            resultView.showEmptyNewsMessage();
        }
    }

    private void sendErrorToView(){
        this.decrementIdleResource();
        resultView.showErrorMessage();
    }

    // -----------------
    // FOR TESTING
    // -----------------

    @VisibleForTesting
    protected List<ArticleSearch> getArticlesForTesting(){
        return this.articles;
    }

    @VisibleForTesting
    public CountingIdlingResource getEspressoIdlingResource() { return espressoTestIdlingResource; }

    @VisibleForTesting
    private void configureEspressoIdlingResource(){
        this.espressoTestIdlingResource = new CountingIdlingResource("Network_Call");
    }

    protected void incrementIdleResource(){
        if (BuildConfig.DEBUG) this.espressoTestIdlingResource.increment();
    }

    protected void decrementIdleResource(){
        if (BuildConfig.DEBUG) this.espressoTestIdlingResource.decrement();
    }

}
