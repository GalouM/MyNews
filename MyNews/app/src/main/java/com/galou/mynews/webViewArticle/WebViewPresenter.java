package com.galou.mynews.webViewArticle;

/**
 * Created by galou on 2019-04-16
 */
public class WebViewPresenter implements WebViewContract.Presenter {

    private WebViewContract.View webViewView;
    private String url;

    public WebViewPresenter(WebViewContract.View webViewView, String url) {
        this.webViewView = webViewView;
        this.url = url;

        webViewView.setPresenter(this);
    }

    @Override
    public void setUpUrl() {
        if (url != null && url.length() > 0){
            webViewView.showWebUrl(url);
        } else {
            webViewView.showSnackBar();
        }

    }

}
