package com.galou.mynews.webViewArticle;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewView extends Fragment implements WebViewContract.View {

    @BindView(R.id.web_view) WebView webView;
    @BindView(R.id.web_view_root_view) CoordinatorLayout rootView;

    private WebViewContract.Presenter presenter;


    public WebViewView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web_view_view, container, false);
        ButterKnife.bind(this, view);
        presenter.setUpUrl();
        return view;
    }

    @Override
    public void setPresenter(WebViewContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void showWebUrl(String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    @Override
    public void showSnackBar(){
        Snackbar snackbar = Snackbar.make(rootView, R.string.incorrect_url, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.hide_button_snackbar, view -> snackbar.dismiss());
        snackbar.show();

    }


}
