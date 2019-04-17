package com.galou.mynews.consultArticles;

import com.galou.mynews.BaseContractView;
import com.galou.mynews.models.ArticleMostPopular;
import com.galou.mynews.models.ArticleTopStories;

import java.util.List;

/**
 * Created by galou on 2019-04-03
 */
public interface ArticleListContract {
    interface View extends BaseContractView<Presenter> {
        void showErrorMessage();
        void setupRecyclerViewMostPopular(List<ArticleMostPopular> articles);
        void setupRecyclerViewTopStories(List<ArticleTopStories> articles);
        void showDetailsArticle(String url, android.view.View v);
        void showEmptyNewsMessage();
    }
    interface Presenter {
        void getArticlesFromNYT(String section);
        void getUrlArticleMostPopular(ArticleMostPopular article, android.view.View v);
        void getUrlArticleTopStories(ArticleTopStories article, android.view.View v);
        void disposeWhenDestroy();

    }
}
