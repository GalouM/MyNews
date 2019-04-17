package com.galou.mynews.resultsSearch;

import com.galou.mynews.models.ArticleSearch;

import java.util.List;

/**
 * Created by galou on 2019-04-09
 */
public interface ResultSearchContract {
    interface View {
        void setPresenter(ResultSearchContract.Presenter presenter);
        void showArticles(List<ArticleSearch> articles);
        void showNextArticles(List<ArticleSearch> articles);
        void showErrorMessage();
        void showDetailsArticle(String url, android.view.View v);
        void showEmptyNewsMessage();
        void showNoMoreNews();
    }
    interface Presenter {
        void getUrlArticle(ArticleSearch article, android.view.View v);
        void getArticles();
        void getNextArticles();
        void disposeWhenDestroy();
    }
}
