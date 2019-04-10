package com.galou.mynews.resultsSearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;

/**
 * Created by galou on 2019-04-09
 */
public class ResultSearchPresenter implements ResultSearchContract.Presenter {

    private ResultSearchContract.View resultView;
    private SectionSearch searchQuery;

    public ResultSearchPresenter(@NonNull ResultSearchContract.View resultView, @Nullable SectionSearch searchQuery){
        this.resultView = resultView;
        this.searchQuery = searchQuery;
        this.resultView.setPresenter(this);

    }

    @Override
    public void getArticles() {
        if (searchQuery.getResponse().getDocs().size() > 0){
            sendArticlesToView();
        } else {
            sendErrorToView();

        }

    }

    @Override
    public void getUrlArticle(ArticleSearch article) {
        resultView.showDetailsArticle(article.getWebUrl());

    }

    private void sendArticlesToView(){
        resultView.showArticles(searchQuery.getResponse().getDocs());
    }

    private void sendErrorToView(){
        resultView.showErrorMessage();
    }
}
