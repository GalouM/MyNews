package com.galou.mynews.resultsSearch;

import com.galou.mynews.BaseContractView;
import com.galou.mynews.consultArticles.ArticleListContract;
import com.galou.mynews.models.ArticleSearch;

import java.util.List;

/**
 * Created by galou on 2019-04-09
 */
public interface ResultSearchContract {
    interface View {
        void setPresenter(ResultSearchContract.Presenter presenter);
        void showArticles(List<ArticleSearch> articles);
        void showErrorMessage();
        void showDetailsArticle(String url);
        void showEmptyNewsMessage();
    }
    interface Presenter {
        void getUrlArticle(ArticleSearch article);
        void getArticles();
        void disposeWhenDestroy();
    }
}
