package com.galou.mynews.webViewArticle;

/**
 * Created by galou on 2019-04-16
 */
public interface WebViewContract {
    interface View {
        void setPresenter(WebViewContract.Presenter presenter);
        void showWebUrl(String url);
        void showSnackBar();

    }
    interface Presenter {
        void setUpUrl();

    }
}
