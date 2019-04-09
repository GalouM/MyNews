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
        void showSnackBar();
        void setupRecyclerViewMostPopular(List<ArticleMostPopular> articles);
        void setupRecyclerViewTopStories(List<ArticleTopStories> articles);
        void startWebViewArticle(String url);
    }
    interface Presenter {
        void getArticlesFromNYT(String section);
        void getUrlArticleMostPopular(ArticleMostPopular article);
        void getUrlArticleTopStories(ArticleTopStories article);
        void disposeWhenDestroy();

    }
}
